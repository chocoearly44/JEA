package tk.thesuperlab.jea.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Grade {
	@JsonProperty("id")
	private int id;
	@JsonProperty("type_name")
	private String typeName;
	@JsonProperty("comment")
	private String comment;
	@JsonProperty("value")
	private String value;

	public Grade() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
