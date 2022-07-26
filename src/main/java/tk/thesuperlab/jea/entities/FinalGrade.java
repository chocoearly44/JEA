package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinalGrade {
	@JsonProperty("value")
	private String value;

	public FinalGrade() {
	}
}
