package tk.thesuperlab.jea.parseentities.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	@JsonProperty("id")
	private Long id;

	public User() {
	}
}
