package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
}
