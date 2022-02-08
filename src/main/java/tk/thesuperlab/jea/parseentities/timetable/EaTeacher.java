package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EaTeacher {
	@JsonProperty("name")
	private String name;

	public EaTeacher() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
