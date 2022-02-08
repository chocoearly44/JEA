package tk.thesuperlab.jea.parseentities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxPrijava {
	@JsonProperty("status")
	private String status;

	public AjaxPrijava() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
