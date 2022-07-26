package tk.thesuperlab.jea.parseentities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tk.thesuperlab.jea.entities.Subject;

import java.util.ArrayList;

@Getter
@Setter
public class SubjectList {
	@JsonProperty("items")
	private ArrayList<Subject> subjects;

	public SubjectList() {
	}
}
