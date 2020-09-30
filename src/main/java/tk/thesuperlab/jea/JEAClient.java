package tk.thesuperlab.jea;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Z JEAClient je glavni razred s katerim lahko kličete ostale metode.
 * @author JurijTSL
 */
public class JEAClient extends Object{
	OkHttpClient client = new OkHttpClient();
	JEAToken tokenCombo;
	
	/**
	 * @param jeatoken - JEAToken za prijavo.
	 */
	public JEAClient(JEAToken jeatoken) {
		this.tokenCombo = jeatoken;
	}
	
	/**
	 * Metoda vam omogoča, da dobite niz preteklih ocenjevanj.
	 * @return JSON niz s preteklimi ocenjevanji
	 * @author JurijTSL
	 * @author LevecGG
	 */
	public String getPastEvaluations() {
		String toReturn = "";
		
        Request getEvaluations = new Request.Builder()
        		.url("https://www.easistent.com/m/evaluations?filter=past")
		        .addHeader("authorization", tokenCombo.bearerToken)
		        .addHeader("x-child-id", tokenCombo.childId)
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
	 */
	public String getFutureEvaluations() {
		String toReturn = "";
		
        Request getEvaluations = new Request.Builder()
        		.url("https://www.easistent.com/m/evaluations?filter=future")
		        .addHeader("authorization", tokenCombo.bearerToken)
		        .addHeader("x-child-id", tokenCombo.childId)
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
	 */
	public String getSchedule() {
		String toReturn = "";
		
        Request getSchedule = new Request.Builder()
        		.url("https://www.easistent.com/m/timetable/weekly")
		        .addHeader("authorization", tokenCombo.bearerToken)
		        .addHeader("x-child-id", tokenCombo.childId)
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
	 */
	public String getGrades() {
		String toReturn = "";
		
        Request getGrades = new Request.Builder()
        		.url("https://www.easistent.com/m/grades")
		        .addHeader("authorization", tokenCombo.bearerToken)
		        .addHeader("x-child-id", tokenCombo.childId)
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
}