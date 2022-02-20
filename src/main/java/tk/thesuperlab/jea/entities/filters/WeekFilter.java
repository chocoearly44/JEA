package tk.thesuperlab.jea.entities.filters;

/**
 * Tedenski filter za urnik
 *
 * @author chocoearly44
 * @since 2.1
 */
public enum WeekFilter {
	/**
	 * Pridobi urnik za prejšnji teden
	 */
	LAST_WEEK,

	/**
	 * Pridobi urnik za trenutni (tekoči) teden
	 */
	CURRENT_WEEK,

	/**
	 * Pridobi urnik za naslednji teden
	 */
	NEXT_WEEK
}
