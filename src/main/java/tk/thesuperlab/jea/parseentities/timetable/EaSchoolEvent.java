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
	private EaSchoolEventSubject subject;
	@JsonProperty("hour_special_type")
	private String specialType;
	@JsonProperty("classroom")
	private EaSchoolEventClassroom classroom;
	@JsonProperty("teachers")
	private List<EaTeacher> teachers;

	public EaSchoolEvent() {
	}
}
