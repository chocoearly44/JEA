package tk.thesuperlab.jea.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opentimetable.ottf4j.OpenTimetable;
import org.opentimetable.ottf4j.entities.*;
import org.opentimetable.ottf4j.exceptions.TimetableComposeException;
import tk.thesuperlab.jea.parseentities.timetable.EaTeacher;
import tk.thesuperlab.jea.parseentities.timetable.EaTimetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OttfUtils {
	private static final ObjectMapper om;

	static {
		om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static Timetable convertTimetable(String rawSchedule) throws JsonProcessingException {
		Timetable toReturn = new Timetable();
		EaTimetable eaTimetable = om.readValue(rawSchedule, EaTimetable.class);

		// Set cues
		Cues cues = new Cues();

		// Set recesses
		List<Span> recesses = eaTimetable.getPeriods().stream()
				.filter(eaPeriod -> eaPeriod.getType().equals("break"))
				.map(eaPeriod -> {
					Span span = new Span();
					span.setFrom(eaPeriod.getTime().getFrom());
					span.setTo(eaPeriod.getTime().getTo());
					return span;
				})
				.collect(Collectors.toList());
		cues.setRecesses(recesses);

		// Set periods
		HashMap<Long, Span> eaSpanMap = new HashMap<>();

		List<Span> periods = eaTimetable.getPeriods().stream()
				.filter(eaPeriod -> eaPeriod.getType().equals("default"))
				.map(eaPeriod -> {
					Span span = new Span();
					span.setFrom(eaPeriod.getTime().getFrom());
					span.setTo(eaPeriod.getTime().getTo());

					eaSpanMap.put(eaPeriod.getId(), span);
					return span;
				})
				.collect(Collectors.toList());

		TreeMap<String, Span> orderedPeriods = new TreeMap<>();
		for(int i = 1; i <= periods.size(); i++) {
			orderedPeriods.put(String.valueOf(i), periods.get(i - 1));
		}
		cues.setPeriods(orderedPeriods);
		toReturn.setCues(cues);

		// Set events
		// Map periods
		HashMap<Span, String> periodMap = new HashMap<>();
		int j = 1;
		for(Span span : orderedPeriods.values()) {
			periodMap.put(span, String.valueOf(j));
			j++;
		}

		// Parse school events
		TreeMap<String, Day> days = new TreeMap<>();
		eaTimetable.getSchoolEvents().stream()
				.forEach(eaSchoolEvent -> {
					Long eaPeriodId = eaSchoolEvent.getTime().getPeriodId();
					String periodSequence = periodMap.get(eaSpanMap.get(eaPeriodId));

					String date = eaSchoolEvent.getTime().getDate();
					String specialType = eaSchoolEvent.getSpecialType();

					// Days
					Day day = days.getOrDefault(date, new Day(new TreeMap<>(), new ArrayList<>(), new ArrayList<>()));

					// Classes
					List<ClassEvent> currentPeriod = day.getClasses().getOrDefault(periodSequence, new ArrayList<>());

					currentPeriod.add(
							new ClassEvent(
									specialType != null && specialType.equals("substitution"),
									specialType != null && specialType.equals("exam"),
									specialType != null && specialType.equals("cancelled"),
									eaSchoolEvent.getSubject().getName(),
									eaSchoolEvent.getSubject().getName(),
									eaSchoolEvent.getClassroom().getName(),
									eaSchoolEvent.getTeachers().stream()
											.map(EaTeacher::getName)
											.collect(Collectors.toList())
							)
					);
					day.getClasses().put(periodSequence, currentPeriod);
					days.put(date, day);
				});
		toReturn.setDays(days);

		try {
			System.out.println(OpenTimetable.composeTimetable(toReturn));
		} catch(TimetableComposeException e) {
			throw new RuntimeException(e);
		}

		return toReturn;
	}
}
