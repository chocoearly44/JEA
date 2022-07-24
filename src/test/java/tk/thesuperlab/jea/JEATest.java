package tk.thesuperlab.jea;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tk.thesuperlab.jea.exceptions.IncorrectCredentialsException;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		jea.getTimetable(new Date(122, 0, 31), new Date(122, 1, 6));
	}

	@Test
	void testGetTimetable() {
	}

	@Test
	void getAllGrades() {
	}
}