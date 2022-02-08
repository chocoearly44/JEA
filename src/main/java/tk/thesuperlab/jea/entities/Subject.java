package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Subject {
	@JsonProperty("name")
	private String name;
	@JsonProperty("short_name")
	private String abbreviation;
	@JsonProperty("id")
	private String id;
	@JsonProperty("is_excused")
	private boolean isExcused;
	@JsonProperty("final_grade")
	private String finalGrade;
	@JsonProperty("average_grade")
	private String averageGrade;
	@JsonProperty("grade_rank")
	private GradeRank gradeRank;
	@JsonProperty("semesters")
	private ArrayList<Semester> semesters;

	public Subject() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isExcused() {
		return isExcused;
	}

	public void setExcused(boolean excused) {
		isExcused = excused;
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	public String getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(String averageGrade) {
		this.averageGrade = averageGrade;
	}

	public GradeRank getGradeRank() {
		return gradeRank;
	}

	public void setGradeRank(GradeRank gradeRank) {
		this.gradeRank = gradeRank;
	}

	public ArrayList<Semester> getSemesters() {
		return semesters;
	}

	public void setSemesters(ArrayList<Semester> semesters) {
		this.semesters = semesters;
	}
}
