package tk.thesuperlab.jea;

import java.io.IOException;
import okhttp3.*;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import tk.thesuperlab.jea.exceptions.IncorrectCredentialsException;

/**
 * JEAClient je glavni razred s katerim lahko kličete ostale metode.
 * @author JurijTSL
 */
public class JEAClient extends Object{
	OkHttpClient client = new OkHttpClient();

	String uporabniskoIme = "";
	String geslo = "";

	/**
	 * @param jeatoken - JEAToken za prijavo.
	 */
	public JEAClient(JEAToken jeatoken) {
		this.uporabniskoIme = jeatoken.uporabniskoIme;
		this.geslo = jeatoken.geslo;
	}

	/**
	 * Metoda vam omogoča, da dobite niz preteklih ocenjevanj.
	 * @return JSON niz s preteklimi ocenjevanji
	 * @author JurijTSL
	 * @author LevecGG
	 * @throws IncorrectCredentialsException
	 */
	public String getPastEvaluations() throws IncorrectCredentialsException {
		String toReturn = "";
		TokenCombo combo = getAccessToken();

		Request getEvaluations = new Request.Builder()
				.url("https://www.easistent.com/m/evaluations?filter=past")
				.addHeader("authorization",combo.bearerToken)
				.addHeader("x-child-id", combo.childId)
				.addHeader("x-client-platform", "web")
				.addHeader("x-client-version", "13")
				.get()
				.build();

		try (Response pastEvaluations = client.newCall(getEvaluations).execute()) {
			toReturn = pastEvaluations.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

	/**
	 *Metoda vam omogoča, da dobite niz prihodnjih ocenjevanj.
	 * @return JSON niz s prihodnjimi ocenjevanji
	 * @author JurijTSL
	 * @author LevecGG
	 * @throws IncorrectCredentialsException
	 */
	public String getFutureEvaluations() throws IncorrectCredentialsException {
		String toReturn = "";
		TokenCombo combo = getAccessToken();

		Request getEvaluations = new Request.Builder()
				.url("https://www.easistent.com/m/evaluations?filter=future")
				.addHeader("authorization", combo.bearerToken)
				.addHeader("x-child-id", combo.childId)
				.addHeader("x-client-platform", "web")
				.addHeader("x-client-version", "13")
				.get()
				.build();

		try (Response futureEvaluations = client.newCall(getEvaluations).execute()) {
			toReturn = futureEvaluations.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

	/**
	 * Metoda vam omogoča, da dobite niz z urnikom za tekoči teden.
	 * @return JSON niz z urnikom za tekoči teden
	 * @author JurijTSL
	 * @author LevecGG
	 * @throws IncorrectCredentialsException
	 */
	public String getSchedule() throws IncorrectCredentialsException {
		String toReturn = "";
		TokenCombo combo = getAccessToken();

		Request getSchedule = new Request.Builder()
				.url("https://www.easistent.com/m/timetable/weekly")
				.addHeader("authorization", combo.bearerToken)
				.addHeader("x-child-id", combo.childId)
				.addHeader("x-client-platform", "web")
				.addHeader("x-client-version", "13")
				.get()
				.build();

		try (Response schedule = client.newCall(getSchedule).execute()) {
			toReturn = schedule.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

	/**
	 * Metoda vam omogoča, da dobite niz z vašimi ocenami.
	 * @return JSON niz z vašimi ocenami
	 * @author JurijTSL
	 * @author LevecGG
	 * @throws IncorrectCredentialsException
	 */
	public String getGrades() throws IncorrectCredentialsException {
		String toReturn = "";
		TokenCombo combo = getAccessToken();

		Request getGrades = new Request.Builder()
				.url("https://www.easistent.com/m/grades")
				.addHeader("authorization", combo.bearerToken)
				.addHeader("x-child-id", combo.childId)
				.addHeader("x-client-platform", "web")
				.addHeader("x-client-version", "13")
				.get()
				.build();

		try (Response grades = client.newCall(getGrades).execute()) {
			toReturn = grades.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

	/**
	 * Interna metoda za pridobivanje Bearer Tokena in Child IDja.
	 * @throws IncorrectCredentialsException
	 */
	private TokenCombo getAccessToken() throws IncorrectCredentialsException {
		String bearerToken = "";
		String childId = "";
		OkHttpClient client = new OkHttpClient();

		RequestBody body = new FormBody.Builder()
				.add("uporabnik", uporabniskoIme)
				.add("geslo", geslo)
				.build();

		Request request = new Request.Builder()
				.url("https://www.easistent.com/p/ajax_prijava")
				.post(body)
				.build();

		try (Response response = client.newCall(request).execute()) {
			String easistentResponse = response.body().string();
			JSONObject jsonDocument = new JSONObject(easistentResponse);
			String authStatus = jsonDocument.getString("status");

			if(authStatus.equals("ok")) {
				Request getWebsite = new Request.Builder().url("https://www.easistent.com").addHeader("Cookie", response.headers().get("Set-Cookie")).build();

				try (Response easistentWebsite = client.newCall(getWebsite).execute()) {
					String eaWebsite = easistentWebsite.body().string();
					Document website = Jsoup.parse(eaWebsite, "utf-8");

					Elements metaElements = website.select("meta");
					for (int i = 0; i < metaElements.size(); i++) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new TokenCombo(bearerToken, childId);
	}
}