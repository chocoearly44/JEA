package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Grade {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("type_name")
	private String typeName;
	@JsonProperty("comment")
	private String comment;
	@JsonProperty("value")
	private String value;

	public Grade() {
	}
}
