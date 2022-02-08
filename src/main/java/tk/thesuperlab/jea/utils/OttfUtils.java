package tk.thesuperlab.jea.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.opentimetable.javaottf.entities.*;
import org.opentimetable.javaottf.enums.WeekDay;
import tk.thesuperlab.jea.parseentities.timetable.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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
		TreeMap<String, Span> periods = new TreeMap<String, Span>();
		HashMap<Integer, Span> periodIds = new HashMap<Integer, Span>();
		ArrayList<Span> recesses = new ArrayList<Span>();

		for(EaPeriod eaPeriod : eaTimetable.getPeriods()) {
			Span span = new Span();
			span.setFrom(eaPeriod.getTime().getFrom());
			span.setTo(eaPeriod.getTime().getTo());

			if(eaPeriod.getType().equals("default")) {
				periods.put(eaPeriod.getName(), span);
				periodIds.put(eaPeriod.getId(), span);
			} else if(eaPeriod.getType().equals("break")) {
				recesses.add(span);
			}
		}

		cues.setPeriods(periods);
		cues.setRecesses(recesses);

		// Set days
		HashMap<WeekDay, Day> days = new HashMap<WeekDay, Day>();

		for(EaDay eaDay : eaTimetable.getDays()) {
			Day day = new Day();
			WeekDay weekDay = null;

			day.setDate(eaDay.getDate());
			day.setPreclasses(new TreeMap<String, List<ClassEvent>>());
			day.setClasses(new TreeMap<String, List<ClassEvent>>());
			day.setEvents(new ArrayList<Event>());
			day.setDayevents(new ArrayList<DayEvent>());

			switch(eaDay.getName()) {
				case "Ponedeljek":
					weekDay = WeekDay.MON;
					break;
				case "Torek":
					weekDay = WeekDay.TUE;
					break;
				case "Sreda":
					weekDay = WeekDay.WED;
					break;
				case "Četrtek":
					weekDay = WeekDay.THU;
					break;
				case "Petek":
					weekDay = WeekDay.FRI;
					break;
			}

			days.put(weekDay, day);
		}

		// Set Classes
		HashMap<String, Day> tempDays = new HashMap<String, Day>();

		for(Day dayScan : days.values()) {
			tempDays.put(dayScan.getDate(), dayScan);
		}

		for(EaSchoolEvent periodEvent : eaTimetable.getSchoolEvents()) {
			Day dayToModify = tempDays.get(periodEvent.getTime().getDate());

			ClassEvent classToAdd = new ClassEvent();

			if(periodEvent.getSpecialType() != null && !periodEvent.getSpecialType().isEmpty()) {
				classToAdd.setSubstitution(periodEvent.getSpecialType().equals("substitution"));
				classToAdd.setExamination(periodEvent.getSpecialType().equals("exam"));
				classToAdd.setCanceled(periodEvent.getSpecialType().equals("cancelled"));
			}

			classToAdd.setName(periodEvent.getSubject().getName());
			classToAdd.setAbbreviation(periodEvent.getSubject().getName());
			classToAdd.setLocation(periodEvent.getClassroom().getName());
			classToAdd.setHosts(new ArrayList<String>());

			for(EaTeacher eaTeacher : periodEvent.getTeachers()) {
				classToAdd.getHosts().add(eaTeacher.getName());
			}

			Span classSpan = periodIds.get(periodEvent.getTime().getPeriodId());
			String spanName = "";

			for(String name : periods.keySet()) {
				if(periods.get(name) == classSpan) {
					spanName = name;
					break;
				}
			}

			dayToModify.getClasses().computeIfAbsent(spanName, k -> new ArrayList<ClassEvent>());

			ArrayList<ClassEvent> classesToModify = (ArrayList<ClassEvent>) dayToModify.getClasses().get(spanName);
			classesToModify.add(classToAdd);

			dayToModify.getClasses().put(spanName, classesToModify);
		}

		// Set events
		ArrayList<Event> events = new ArrayList<Event>();

		for(EaEvent eaEvent : eaTimetable.getEvents()) {
			Event eventToAdd = new Event();
			eventToAdd.setFrom(eaEvent.getTime().getFrom());
			eventToAdd.setTo(eaEvent.getTime().getTo());
			eventToAdd.setTitle(eaEvent.getName());

			ArrayList<String> eventTeachers = new ArrayList<String>();
			for(EaTeacher teacher : eaEvent.getTeachers()) {
				eventTeachers.add(teacher.getName());
			}

			eventToAdd.setHosts(eventTeachers);

			Day dayToModify = tempDays.get(eaEvent.getDate());
			if(dayToModify.getEvents() != null) {
				dayToModify.setEvents(new ArrayList<Event>());
			}

			dayToModify.getEvents().add(eventToAdd);
		}

		// Set day events
		ArrayList<DayEvent> dayEvents = new ArrayList<DayEvent>();

		for(EaDayEvent eaDayEvent : eaTimetable.getDayEvents()) {
			DayEvent dayEventToAdd = new DayEvent();
			dayEventToAdd.setTitle(eaDayEvent.getName());

			ArrayList<String> eventTeachers = new ArrayList<String>();
			for(EaTeacher teacher : eaDayEvent.getTeachers()) {
				eventTeachers.add(teacher.getName());
			}

			dayEventToAdd.setHosts(eventTeachers);

			Day dayToModify = tempDays.get(eaDayEvent.getDate());
			if(dayToModify.getDayevents() != null) {
				dayToModify.setDayevents(new ArrayList<DayEvent>());
			}

			dayToModify.getDayevents().add(dayEventToAdd);
		}

		toReturn.setVersion("1.0");
		toReturn.setCues(cues);
		toReturn.setDays(days);

		return toReturn;
	}
}
