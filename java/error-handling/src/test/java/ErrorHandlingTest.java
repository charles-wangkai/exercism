import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ErrorHandlingTest {

	private ErrorHandling errorHandling;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		errorHandling = new ErrorHandling();
	}

	@Test
	public void testThrowIllegalArgumentException() {
		thrown.expect(IllegalArgumentException.class);
		errorHandling.handleErrorByThrowingIllegalArgumentException();
	}

	@Test
	public void testThrowIllegalArgumentExceptionWithDetailMessage() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("This is the detail message.");
		errorHandling.handleErrorByThrowingIllegalArgumentExceptionWithDetailMessage("This is the detail message.");
	}

	@Test
	public void testThrowAnyCheckedException() {
		try {
			errorHandling.handleErrorByThrowingAnyCheckedException();
		} catch (Exception e) {
			assertFalse(e instanceof RuntimeException);
		}
	}

	@Test
	public void testThrowAnyCheckedExceptionWithDetailMessage() {
		try {
			errorHandling.handleErrorByThrowingAnyCheckedExceptionWithDetailMessage("This is the detail message.");
		} catch (Exception e) {
			assertFalse(e instanceof RuntimeException);
			assertEquals("This is the detail message.", e.getMessage());
		}
	}

	@Test
	public void testThrowAnyUncheckedException() {
		thrown.expect(RuntimeException.class);
		errorHandling.handleErrorByThrowingAnyUncheckedException();
	}

	@Test
	public void testThrowAnyUncheckedExceptionWithDetailMessage() {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("This is the detail message.");
		errorHandling.handleErrorByThrowingAnyUncheckedExceptionWithDetailMessage("This is the detail message.");
	}

	@Test
	public void testThrowCustomCheckedException() throws CustomCheckedException {
		thrown.expect(CustomCheckedException.class);
		errorHandling.handleErrorByThrowingCustomCheckedException();
	}

	@Test
	public void testThrowCustomCheckedExceptionWithDetailMessage() throws CustomCheckedException {
		thrown.expect(CustomCheckedException.class);
		thrown.expectMessage("This is the detail message.");
		errorHandling.handleErrorByThrowingCustomCheckedExceptionWithDetailMessage("This is the detail message.");
	}

	@Test
	public void testThrowCustomUncheckedException() {
		thrown.expect(CustomUncheckedException.class);
		errorHandling.handleErrorByThrowingCustomUncheckedException();
	}

	@Test
	public void testThrowCustomUncheckedExceptionWithDetailMessage() {
		thrown.expect(CustomUncheckedException.class);
		thrown.expectMessage("This is the detail message.");
		errorHandling.handleErrorByThrowingCustomUncheckedExceptionWithDetailMessage("This is the detail message.");
	}

	@Test
	public void testReturnOptionalInstance() {
		Optional<Integer> successfulResult = errorHandling.handleErrorByReturningOptionalInstance("1");
		assertTrue(successfulResult.isPresent());
		assertEquals(1, (int) successfulResult.get());

		Optional<Integer> failureResult = errorHandling.handleErrorByReturningOptionalInstance("a");
		assertFalse(failureResult.isPresent());
	}

}
