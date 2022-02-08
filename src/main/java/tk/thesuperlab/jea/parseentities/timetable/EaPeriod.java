package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EaPeriod {
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("time")
	private EaTime time;
	@JsonProperty("type")
	private String type;

	public EaPeriod() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
