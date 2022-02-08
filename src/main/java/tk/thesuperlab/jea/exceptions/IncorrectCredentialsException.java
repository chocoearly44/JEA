package tk.thesuperlab.jea.exceptions;

/**
 * Razred za napačno uporabniško ime in geslo
 */
public class IncorrectCredentialsException extends Exception {
	public IncorrectCredentialsException() {
		super("Napačno uporabniško ime ali geslo.");
	}
}
