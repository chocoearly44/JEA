package tk.thesuperlab.jea.parseentities;

import com.fasterxml.jackson.annotation.JsonProperty;
import tk.thesuperlab.jea.entities.Subject;

import java.util.ArrayList;

public class SubjectList {
	@JsonProperty("items")
	private ArrayList<Subject> subjects;

	public SubjectList() {
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<Subject> subjects) {
		this.subjects = subjects;
	}
}
