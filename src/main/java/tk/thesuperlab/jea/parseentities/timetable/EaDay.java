package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EaDay {
	@JsonProperty("name")
	private String name;
	@JsonProperty("date")
	private String date;

	public EaDay() {
	}
}