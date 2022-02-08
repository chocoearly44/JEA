package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EaDayEvent {
	@JsonProperty("date")
	private String date;
	@JsonProperty("name")
	private String name;
	@JsonProperty("teachers")
	private List<EaTeacher> teachers;

	public EaDayEvent() {
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

	public List<EaTeacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<EaTeacher> teachers) {
		this.teachers = teachers;
	}
}
