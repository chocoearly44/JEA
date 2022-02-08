package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Evaluation {
	@JsonProperty("id")
	private int id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
}
