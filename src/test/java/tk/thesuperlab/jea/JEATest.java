package tk.thesuperlab.jea;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tk.thesuperlab.jea.exceptions.IncorrectCredentialsException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class JEATest {
	static JEA jea;

	@BeforeAll
	static void setup() throws IncorrectCredentialsException, IOException {
		jea = new JEA(System.getenv("USERNAME"), System.getenv("PASSWORD"));
	}

	@Test
	void getFutureEvaluations() {
		assertNotNull(jea.getFutureEvaluations());
	}

	@Test
	void getPastEvaluations() {
		assertNotNull(jea.getPastEvaluations());
	}

	@Test
	void getTimetable() {
	}

	@Test
	void testGetTimetable() {
	}

	@Test
	void getAllGrades() {
	}
}