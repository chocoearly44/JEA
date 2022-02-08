package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EaSchoolEventClassroom {
	@JsonProperty("name")
	private String name;

	public EaSchoolEventClassroom() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
