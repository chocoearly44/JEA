package tk.thesuperlab.jea.parseentities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import tk.thesuperlab.jea.entities.Evaluation;

import java.util.ArrayList;

@Getter
@Setter
public class EvaluationList {
	@JsonProperty("items")
	ArrayList<Evaluation> evaluations;

	public EvaluationList() {
	}
}
