package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum GradeRank {
	@JsonProperty("poor")
	POOR,
	@JsonProperty("average")
	AVERAGE,
	@JsonProperty("good")
	GOOD;
}
