package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EaEvent {
	@JsonProperty("date")
	private String date;
	@JsonProperty("name")
	private String name;
	@JsonProperty("time")
	private EaTime time;
	@JsonProperty("teachers")
	private List<EaTeacher> teachers;

	public EaEvent() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EaTime getTime() {
		return time;
	}

	public void setTime(EaTime time) {
		this.time = time;
	}

	public List<EaTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<EaTeacher> teachers) {
		this.teachers = teachers;
	}
}
