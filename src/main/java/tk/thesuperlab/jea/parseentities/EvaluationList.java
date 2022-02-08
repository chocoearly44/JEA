package tk.thesuperlab.jea.parseentities;

import com.fasterxml.jackson.annotation.JsonProperty;
import tk.thesuperlab.jea.entities.Evaluation;

import java.util.ArrayList;

public class EvaluationList {
	@JsonProperty("items")
	ArrayList<Evaluation> evaluations;

	public EvaluationList() {
	}

	public ArrayList<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(ArrayList<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
}
