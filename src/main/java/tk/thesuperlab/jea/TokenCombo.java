package tk.thesuperlab.jea;

/**
 * TokenCombo je interni objekt v katerem se shrani bearerToken in childId
 */
class TokenCombo {
	String bearerToken = "";
	String childId = "";

	/**
	 * @param bearerToken - Žeton za vpisovanje
	 * @param childId - ID Otroka
	 */
	protected TokenCombo(String bearerToken, String childId) {
		this.bearerToken = bearerToken;
		this.childId = childId;
	}
}