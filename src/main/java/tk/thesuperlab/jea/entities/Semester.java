package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Semester {
	@JsonProperty("id")
	private int id;
	@JsonProperty("final_grade")
	private String finalGrade;
	@JsonProperty("grades")
	private ArrayList<Grade> grades;

	public Semester() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	public ArrayList<Grade> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Grade> grades) {
		this.grades = grades;
	}
}
