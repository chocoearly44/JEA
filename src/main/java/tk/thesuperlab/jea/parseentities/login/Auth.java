package tk.thesuperlab.jea.parseentities.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Auth {
	@JsonProperty("access_token")
	private AccessToken accessToken;
	@JsonProperty("user")
	private User user;

	public Auth() {
	}
}
