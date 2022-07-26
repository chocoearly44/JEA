package tk.thesuperlab.jea.parseentities.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Credentials {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
	private String password;
	@JsonProperty("supported_user_types")
	private List<String> supportedUserTypes;

	public Credentials(String username, String password, List<String> supportedUserTypes) {
		this.username = username;
		this.password = password;
		this.supportedUserTypes = supportedUserTypes;
	}

	public Credentials() {
	}
}
