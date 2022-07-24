package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EaTime {
	@JsonProperty("from")
	private String from;
	@JsonProperty("to")
	private String to;

	public EaTime() {
	}
}
