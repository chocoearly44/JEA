package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EaEvent {
	@JsonProperty("date")
	private String date;
	@JsonProperty("name")
	private String name;
	@JsonProperty("time")
	private EaTime time;
	@JsonProperty("teachers")
	private List<EaTeacher> teachers;

	public EaEvent() {
	}
}
