package tk.thesuperlab.jea.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.opentimetable.javaottf.entities.Timetable;
import tk.thesuperlab.jea.entities.Evaluation;
import tk.thesuperlab.jea.entities.Subject;
import tk.thesuperlab.jea.parseentities.EvaluationList;
import tk.thesuperlab.jea.parseentities.SubjectList;

import java.io.IOException;
import java.util.ArrayList;

public class RestUtils {
	private static final ObjectMapper om;

	static {
		om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public static ArrayList<Evaluation> getPastEvaluations(String bearerToken, String childId) {
		return getEvaluations(bearerToken, childId, "past");
	}

	public static ArrayList<Evaluation> getFutureEvaluations(String bearerToken, String childId) {
		return getEvaluations(bearerToken, childId, "future");
	}

	public static Timetable getTimetable(String bearerToken, String childId, String monday, String sunday) {
		OkHttpClient client = new OkHttpClient();

		Request getSchedule = new Request.Builder()
				.url("https://www.easistent.com/m/timetable/weekly?from=" + monday + "&to=" + sunday)
				.addHeader("authorization", bearerToken)
				.addHeader("x-child-id", childId)
				.addHeader("x-client-platform", "web")
				.addHeader("x-client-version", "13")
				.get()
				.build();

		try(Response schedule = client.newCall(getSchedule).execute()) {
			return OttfUtils.convertTimetable(schedule.body().string());
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Subject> getAllGrades(String bearerToken, String childId) {
		ArrayList<Subject> toReturn = new ArrayList<Subject>();
		OkHttpClient client = new OkHttpClient();

		Request getGrades = new Request.Builder()
				.url("https://www.easistent.com/m/grades")
				.addHeader("authorization", bearerToken)
				.addHeader("x-child-id", childId)
				.addHeader("x-client-platform", "web")
				.addHeader("x-client-version", "13")
				.get()
				.build();

		try(Response gradesResp = client.newCall(getGrades).execute()) {
			return om.readValue(gradesResp.body().string(), SubjectList.class).getSubjects();
		} catch(IOException e) {
			e.printStackTrace();
		}

		return toReturn;
	}

	private static ArrayList<Evaluation> getEvaluations(String bearerToken, String childId, String filter) {
		OkHttpClient client = new OkHttpClient();

		Request getEvaluations = new Request.Builder()
				.url("https://www.easistent.com/m/evaluations?filter=" + filter)
				.addHeader("authorization", bearerToken)
				.addHeader("x-child-id", childId)
				.addHeader("x-client-platform", "web")
				.addHeader("x-client-version", "13")
				.get()
				.build();

		try(Response futureEvaluations = client.newCall(getEvaluations).execute()) {
			return om.readValue(futureEvaluations.body().string(), EvaluationList.class).getEvaluations();
		} catch(Exception e) {
			e.printStackTrace();
			return new ArrayList<Evaluation>();
		}
	}
}