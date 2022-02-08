package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EaTimetable {
	@JsonProperty("time_table")
	private List<EaPeriod> periods;
	@JsonProperty("day_table")
	private List<EaDay> days;
	@JsonProperty("school_hour_events")
	private List<EaSchoolEvent> schoolEvents;
	@JsonProperty("events")
	private List<EaEvent> events;
	@JsonProperty("all_day_events")
	private List<EaDayEvent> dayEvents;

	public EaTimetable() {
	}

	public List<EaPeriod> getPeriods() {
		return periods;
	}

	public void setPeriods(List<EaPeriod> periods) {
		this.periods = periods;
	}

	public List<EaDay> getDays() {
		return days;
	}

	public void setDays(List<EaDay> days) {
		this.days = days;
	}

	public List<EaSchoolEvent> getSchoolEvents() {
		return schoolEvents;
	}

	public void setSchoolEvents(List<EaSchoolEvent> schoolEvents) {
		this.schoolEvents = schoolEvents;
	}

	public List<EaEvent> getEvents() {
		return events;
	}

	public void setEvents(List<EaEvent> events) {
		this.events = events;
	}

	public List<EaDayEvent> getDayEvents() {
		return dayEvents;
	}

	public void setDayEvents(List<EaDayEvent> dayEvents) {
		this.dayEvents = dayEvents;
	}
}
