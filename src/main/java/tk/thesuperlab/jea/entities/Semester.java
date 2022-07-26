package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Semester {
	@JsonProperty("id")
	private Long id;
	@JsonProperty("final_grade")
	private String finalGrade;
	@JsonProperty("grades")
	private ArrayList<Grade> grades;

	public Semester() {
	}
}
