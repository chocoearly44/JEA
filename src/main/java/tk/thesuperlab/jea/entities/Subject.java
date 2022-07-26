package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Subject {
	@JsonProperty("name")
	private String name;
	@JsonProperty("short_name")
	private String abbreviation;
	@JsonProperty("id")
	private String id;
	@JsonProperty("is_excused")
	private Boolean isExcused;
	@JsonProperty("final_grade")
	private FinalGrade finalGrade;
	@JsonProperty("average_grade")
	private String averageGrade;
	@JsonProperty("grade_rank")
	private GradeRank gradeRank;
	@JsonProperty("semesters")
	private ArrayList<Semester> semesters;

	public Subject() {
	}
}
