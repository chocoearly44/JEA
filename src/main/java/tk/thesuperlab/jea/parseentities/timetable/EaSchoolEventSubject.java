package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EaSchoolEventSubject {
	@JsonProperty("name")
	private String name;

	public EaSchoolEventSubject() {
	}
}
