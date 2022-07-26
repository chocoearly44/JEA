package tk.thesuperlab.jea;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.opentimetable.ottf4j.entities.Timetable;
import tk.thesuperlab.jea.entities.Evaluation;
import tk.thesuperlab.jea.entities.Subject;
import tk.thesuperlab.jea.exceptions.IncorrectCredentialsException;
import tk.thesuperlab.jea.filters.WeekFilter;
import tk.thesuperlab.jea.parseentities.login.Auth;
import tk.thesuperlab.jea.parseentities.login.Credentials;
import tk.thesuperlab.jea.utils.RestUtils;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Glavni razred za klicanje eAsistent API-ja
 *
 * @author chocoearly44
 * @since 2.0
 */
public class JEA {
	private static final ObjectMapper om;
	private static final Format formatter = new SimpleDateFormat("yyyy-MM-dd");

	static {
		om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	private final String uporabniskoIme;
	private final String geslo;
	private String bearerToken;
	private String childId;

	/**
	 * @param uporabniskoIme Vaše uporabniško ime za prijavo v eAsistent
	 * @param geslo          Vaše geslo za prijavo v eAsistent.
	 * @throws IncorrectCredentialsException Podatki za prijavo so napačni
	 * @throws IOException                   Napaka pri povezavi
	 * @since 2.0
	 */
	public JEA(String uporabniskoIme, String geslo) throws IncorrectCredentialsException, IOException {
		this.uporabniskoIme = uporabniskoIme;
		this.geslo = geslo;

		getAccessToken();
	}

	/**
	 * Metoda vam vrne seznam prihodnjih ocenjevanj
	 *
	 * @return Seznam ocenjevanj
	 * @since 2.0
	 */
	public List<Evaluation> getFutureEvaluations() {
		return RestUtils.getFutureEvaluations(bearerToken, childId);
	}

	/**
	 * Metoda vam vrne seznam preteklih ocenjevanj
	 *
	 * @return Seznam ocenjevanj
	 * @since 2.0
	 */
	public List<Evaluation> getPastEvaluations() {
		return RestUtils.getPastEvaluations(bearerToken, childId);
	}

	/**
	 * Metoda vam vrne OTTF objekt tedenskega urnika
	 *
	 * @param ponedeljek Datum ponedeljka v tednu
	 * @param nedelja    Datum nedelje v tednu
	 * @return OTTF objekt urnika
	 * @since 2.0
	 */
	public Timetable getTimetable(Date ponedeljek, Date nedelja) {
		return RestUtils.getTimetable(bearerToken, childId, formatter.format(ponedeljek), formatter.format(nedelja));
	}

	/**
	 * Metoda vam vrne OTTF objekt tedenskega urnika
	 *
	 * @param ponedeljek Niz ponedeljka v tednu (yyyy-MM-dd)
	 * @param nedelja    Niz nedelje v tednu (yyyy-MM-dd)
	 * @return OTTF objekt urnika
	 * @since 2.0
	 */
	public Timetable getTimetable(String ponedeljek, String nedelja) {
		return RestUtils.getTimetable(bearerToken, childId, ponedeljek, nedelja);
	}

	/**
	 * Metoda vam vrne OTTF objekt tedenskega urnika glede na tedenski filter
	 *
	 * @param tedenskiFilter Tedenski filter
	 * @return OTTF objekt urnika
	 * @since 2.1
	 */
	public Timetable getTimetable(WeekFilter tedenskiFilter) {
		Date monday;
		Date sunday;

		LocalDate currentMonday = LocalDate.now().with(DayOfWeek.MONDAY);
		LocalDate currentSunday = LocalDate.now().with(DayOfWeek.SUNDAY);

		Calendar cal = Calendar.getInstance();

		switch(tedenskiFilter) {
			case LAST_WEEK:
				cal.setTime(getDate(currentMonday));
				cal.add(Calendar.DATE, -7);
				monday = cal.getTime();

				cal.setTime(getDate(currentSunday));
				cal.add(Calendar.DATE, -7);
				sunday = cal.getTime();
				break;

			case NEXT_WEEK:
				cal.setTime(getDate(currentMonday));
				cal.add(Calendar.DATE, 7);
				monday = cal.getTime();

				cal.setTime(getDate(currentSunday));
				cal.add(Calendar.DATE, 7);
				sunday = cal.getTime();
				break;

			default:
				monday = getDate(currentMonday);
				sunday = getDate(currentSunday);
				break;
		}

		return RestUtils.getTimetable(bearerToken, childId, formatter.format(monday), formatter.format(sunday));
	}

	/**
	 * Metoda vam vrne seznam vseh predmetov
	 *
	 * @return seznam vseh predmetov
	 * @since 2.1
	 */
	public List<Subject> getAllGrades() {
		return RestUtils.getAllGrades(bearerToken, childId);
	}

	private Date getDate(LocalDate localDate) {
		return Date.from(
				localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
		);
	}

	private void getAccessToken() throws IncorrectCredentialsException, IOException {
		OkHttpClient client = new OkHttpClient();

		List<String> types = new ArrayList<>();
		types.add("child");

		RequestBody body = RequestBody.create(
				om.writeValueAsString(new Credentials(uporabniskoIme, geslo, types)),
				MediaType.parse("application/json")
		);

		Request request = new Request.Builder()
				.url("https://www.easistent.com/m/login")
				.post(body)
				.addHeader("x-app-name", "family")
				.addHeader("x-client-version", "13")
				.addHeader("x-client-platform", "web")
				.build();

		Response response = client.newCall(request).execute();

		if(response.code() == 401) {
			throw new IncorrectCredentialsException();
		}

		Auth auth = om.readValue(response.body().string(), Auth.class);
		bearerToken = auth.getAccessToken().getBearerToken();
		childId = String.valueOf(auth.getUser().getId());
	}
}
