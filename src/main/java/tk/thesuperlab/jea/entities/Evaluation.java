package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Evaluation {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("course")
	private String course;
	@JsonProperty("subject")
	private String subject;
	@JsonProperty("type")
	private String type;
	@JsonProperty("date")
	private String date;
	@JsonProperty("period")
	private String period;
	@JsonProperty("grade")
	private String grade;

	public Evaluation() {
	}
}
