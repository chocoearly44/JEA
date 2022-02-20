package tk.thesuperlab.jea;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.opentimetable.javaottf.entities.Timetable;
import tk.thesuperlab.jea.entities.Evaluation;
import tk.thesuperlab.jea.entities.Subject;
import tk.thesuperlab.jea.entities.filters.WeekFilter;
import tk.thesuperlab.jea.exceptions.IncorrectCredentialsException;
import tk.thesuperlab.jea.parseentities.AjaxPrijava;
import tk.thesuperlab.jea.utils.RestUtils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
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
	private final String uporabniskoIme;
	private final String geslo;

	private String bearerToken;
	private String childId;

	private static final ObjectMapper om;
	private static final Format formatter = new SimpleDateFormat("yyyy-MM-dd");

	static {
		om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * @param uporabniskoIme Vaše uporabniško ime za prijavo v eAsistent
	 * @param geslo          Vaše geslo za prijavo v eAsistent.
	 * @throws IncorrectCredentialsException
	 * @author chocoearly44
	 * @since 2.0
	 */
	public JEA(String uporabniskoIme, String geslo) throws IncorrectCredentialsException {
		this.uporabniskoIme = uporabniskoIme;
		this.geslo = geslo;

		try {
			getAccessToken();
		} catch(IncorrectCredentialsException e) {
			throw new IncorrectCredentialsException();
		}
	}

	/**
	 * Metoda vam vrne seznam prihodnjih ocenjevanj
	 *
	 * @return Seznam ocenjevanj
	 * @author chocoearly44
	 * @since 2.0
	 */
	public List<Evaluation> getFutureEvaluations() {
		return RestUtils.getFutureEvaluations(bearerToken, childId);
	}

	/**
	 * Metoda vam vrne seznam preteklih ocenjevanj
	 *
	 * @return Seznam ocenjevanj
	 * @author chocoearly44
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
	 * @author chocoearly44
	 * @since 2.0
	 */
	public Timetable getTimetable(Date ponedeljek, Date nedelja) {
		return RestUtils.getTimetable(bearerToken, childId, formatter.format(ponedeljek), formatter.format(nedelja));
	}

	/**
	 * Metoda vam vrne OTTF objekt tedenskega urnika glede na tedenski filter
	 *
	 * @param tedenskiFilter Tedenski filter
	 * @return OTTF objekt urnika
	 * @author chocoearly44
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
	 * @author chocoearly44
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

	private void getAccessToken() throws IncorrectCredentialsException {
		OkHttpClient client = new OkHttpClient();

		RequestBody body = new FormBody.Builder()
				.add("uporabnik", uporabniskoIme)
				.add("geslo", geslo)
				.build();

		Request request = new Request.Builder()
				.url("https://www.easistent.com/p/ajax_prijava")
				.post(body)
				.build();

		try(Response response = client.newCall(request).execute()) {
			AjaxPrijava ajaxPrijava = om.readValue(response.body().string(), AjaxPrijava.class);

			if(ajaxPrijava.getStatus().equals("ok")) {
				Request getWebsite = new Request.Builder().url("https://www.easistent.com").addHeader("Cookie", response.headers().get("Set-Cookie")).build();

				try(Response easistentWebsite = client.newCall(getWebsite).execute()) {
					String eaWebsite = easistentWebsite.body().string();
					Document website = Jsoup.parse(eaWebsite, "utf-8");

					Elements metaElements = website.select("meta");
					for(int i = 0; i < metaElements.size(); i++) {
						if(metaElements.get(i).attr("name").equals("access-token")) {
							bearerToken = metaElements.get(i).attr("content");
						} else if(metaElements.get(i).attr("name").equals("x-child-id")) {
							childId = metaElements.get(i).attr("content");
						}
					}
				}
			} else {
				throw new IncorrectCredentialsException();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
