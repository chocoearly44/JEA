package tk.thesuperlab.jea;

/**
 * Z JEAToken razredom knjižnica dobi dostop <i>(samo za branje)</i> do podatkov do vašega eAsistent profila.
 * @author JurijTSL
 */
public class JEAToken {
	String uporabniskoIme = "";
	String geslo = "";

	/**
	 * @param uporabniskoIme - Vaše uporabniško ime za prijavo v eAsistent.
	 * @param geslo - Vaše geslo za prijavo v eAsistent.
	 */
	public JEAToken(String uporabniskoIme, String geslo) {
		this.uporabniskoIme = uporabniskoIme;
		this.geslo = geslo;
	}
}