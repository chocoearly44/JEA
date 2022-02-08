package tk.thesuperlab.jea.parseentities.timetable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EaSchoolEventTime {
	@JsonProperty("from_id")
	private int periodId;
	@JsonProperty("date")
	private String date;

	public EaSchoolEventTime() {
	}

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
