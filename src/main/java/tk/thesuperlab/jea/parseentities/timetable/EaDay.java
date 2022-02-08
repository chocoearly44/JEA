package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EaDay {
	@JsonProperty("name")
	private String name;
	@JsonProperty("date")
	private String date;

	public EaDay() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}