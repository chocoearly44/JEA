package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EaSchoolEvent {
	@JsonProperty("time")
	private EaSchoolEventTime time;
	@JsonProperty("subject")
	private EaNameElement subject;
	@JsonProperty("hour_special_type")
	private String specialType;
	@JsonProperty("classroom")
	private EaNameElement classroom;
	@JsonProperty("teachers")
	private List<EaNameElement> teachers;

	public EaSchoolEvent() {
	}
}
