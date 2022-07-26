package tk.thesuperlab.jea.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opentimetable.ottf4j.entities.*;
import tk.thesuperlab.jea.parseentities.timetable.EaNameElement;
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

		// Set metadata
		toReturn.setMetadata(
				new Metadata(
						"1.0",
						"CEST",
						"Java eAsistent API",
						System.currentTimeMillis() / 1000
				)
		);

		// Set cues
		Cues cues = new Cues();

		// Set recesses
		List<Span> recesses = eaTimetable.getPeriods().stream()
				.filter(eaPeriod -> eaPeriod.getType().equals("break"))
				.map(eaPeriod -> new Span(
						eaPeriod.getTime().getFrom(),
						eaPeriod.getTime().getTo()
				))
				.collect(Collectors.toList());
		cues.setRecesses(recesses);

		// Set periods
		TreeMap<String, Span> orderedPeriods = new TreeMap<>();
		HashMap<Long, String> periodMap = new HashMap<>();

		eaTimetable.getPeriods().stream()
				.filter(eaPeriod -> eaPeriod.getType().equals("default"))
				.forEachOrdered(eaPeriod -> {
					periodMap.put(eaPeriod.getId(), eaPeriod.getName());
					orderedPeriods.put(
							eaPeriod.getName(),
							new Span(
									eaPeriod.getTime().getFrom(),
									eaPeriod.getTime().getTo()
							)
					);
				});
		cues.setPeriods(orderedPeriods);
		toReturn.setCues(cues);

		// Set days
		TreeMap<String, Day> days = new TreeMap<>();

		// School hour
		eaTimetable.getSchoolEvents()
				.forEach(eaSchoolEvent -> {
					Long eaPeriodId = eaSchoolEvent.getTime().getPeriodId();
					String periodSequence = periodMap.get(eaPeriodId);

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
											.map(EaNameElement::getName)
											.collect(Collectors.toList())
							)
					);
					day.getClasses().put(periodSequence, currentPeriod);
					days.put(date, day);
				});

		// Events
		eaTimetable.getEvents()
				.forEach(eaEvent -> {
					Day eventDay = days.get(eaEvent.getDate());

					if(eventDay == null) {
						eventDay = new Day(new TreeMap<>(), new ArrayList<>(), new ArrayList<>());
						days.put(eaEvent.getDate(), eventDay);
					}

					eventDay.getEvents().add(
							new Event(
									eaEvent.getTime().getFrom(),
									eaEvent.getTime().getTo(),
									eaEvent.getName(),
									eaEvent.getLocation().getName(),
									eaEvent.getTeachers().stream()
											.map(EaNameElement::getName)
											.collect(Collectors.toList())
							)
					);
				});

		// Day events
		eaTimetable.getDayEvents()
				.forEach(eaDayEvent -> {
					Day eventDay = days.get(eaDayEvent.getDate());

					if(eventDay == null) {
						eventDay = new Day(new TreeMap<>(), new ArrayList<>(), new ArrayList<>());
						days.put(eaDayEvent.getDate(), eventDay);
					}

					eventDay.getDayevents().add(
							new DayEvent(
									eaDayEvent.getName(),
									"",
									eaDayEvent.getTeachers().stream()
											.map(EaNameElement::getName)
											.collect(Collectors.toList())
							)
					);
				});
		toReturn.setDays(days);

		return toReturn;
	}
}
