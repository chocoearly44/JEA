package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EaSchoolEventTime {
	@JsonProperty("from_id")
	private Long periodId;
	@JsonProperty("date")
	private String date;

	public EaSchoolEventTime() {
	}
}
