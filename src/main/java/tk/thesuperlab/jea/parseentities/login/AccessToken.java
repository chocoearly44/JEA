package tk.thesuperlab.jea.parseentities.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessToken {
	@JsonProperty("token")
	private String bearerToken;

	public AccessToken() {
	}
}
