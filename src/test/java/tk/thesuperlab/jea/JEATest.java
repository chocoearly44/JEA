package tk.thesuperlab.jea;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tk.thesuperlab.jea.exceptions.IncorrectCredentialsException;
import tk.thesuperlab.jea.filters.WeekFilter;

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
		assertNotNull(jea.getTimetable(WeekFilter.CURRENT_WEEK));
	}

	@Test
	void getAllGrades() {
		assertNotNull(jea.getAllGrades());
	}
}