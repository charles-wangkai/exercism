import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class IsbnVerifierTest {
	private IsbnVerifier isbnVerifier;

	@Before
	public void setUp() {
		isbnVerifier = new IsbnVerifier();
	}

	@Test
	public void validIsbnNumber() {
		assertTrue(isbnVerifier.isValid("3-598-21508-8"));
	}

	@Test
	public void invalidIsbnCheckDigit() {
		assertFalse(isbnVerifier.isValid("3-598-21508-9"));
	}

	@Test
	public void validIsbnNumberWithCheckDigitOfTen() {
		assertTrue(isbnVerifier.isValid("3-598-21507-X"));
	}

	@Test
	public void checkDigitIsACharacterOtherThanX() {
		assertFalse(isbnVerifier.isValid("3-598-21507-A"));
	}

	@Test
	public void invalidCharacterInIsbn() {
		assertFalse(isbnVerifier.isValid("3-598-2K507-0"));
	}

	@Test
	public void xIsOnlyValidAsACheckDigit() {
		assertFalse(isbnVerifier.isValid("3-598-2X507-9"));
	}

	@Test
	public void validIsbnWithoutSeparatingDashes() {
		assertTrue(isbnVerifier.isValid("3598215088"));
	}

	@Test
	public void isbnWithoutSeparatingDashesAndXAsCheckDigit() {
		assertTrue(isbnVerifier.isValid("359821507X"));
	}

	@Test
	public void isbnWithoutCheckDigitAndDashes() {
		assertFalse(isbnVerifier.isValid("359821507"));
	}

	@Test
	public void tooLongIsbnAndNoDashes() {
		assertFalse(isbnVerifier.isValid("3598215078X"));
	}

	@Test
	public void isbnWithoutCheckDigit() {
		assertFalse(isbnVerifier.isValid("3-598-21507"));
	}

	@Test
	public void tooLongIsbn() {
		assertFalse(isbnVerifier.isValid("3-598-21507-XX"));
	}

	@Test
	public void checkDigitOfXShouldNotBeUsedForZero() {
		assertFalse(isbnVerifier.isValid("3-598-21515-X"));
	}

}
