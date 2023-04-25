import static org.assertj.core.api.Assertions.assertThat;

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
    assertThat(isbnVerifier.isValid("3-598-21508-8")).isTrue();
  }

  @Test
  public void invalidIsbnCheckDigit() {
    assertThat(isbnVerifier.isValid("3-598-21508-9")).isFalse();
  }

  @Test
  public void validIsbnNumberWithCheckDigitOfTen() {
    assertThat(isbnVerifier.isValid("3-598-21507-X")).isTrue();
  }

  @Test
  public void validIsbnNumberWithCheckDigitPaddedWithLettersIsInvalid() {
    assertThat(isbnVerifier.isValid("ABCDEFG3-598-21507-XQWERTYUI")).isFalse();
  }

  @Test
  public void checkDigitIsACharacterOtherThanX() {
    assertThat(isbnVerifier.isValid("3-598-21507-A")).isFalse();
  }

  @Test
  public void invalidCharacterInIsbn() {
    assertThat(isbnVerifier.isValid("3-598-P1581-X")).isFalse();
  }

  @Test
  public void xIsOnlyValidAsACheckDigit() {
    assertThat(isbnVerifier.isValid("3-598-2X507-9")).isFalse();
  }

  @Test
  public void validIsbnWithoutSeparatingDashes() {
    assertThat(isbnVerifier.isValid("3598215088")).isTrue();
  }

  @Test
  public void isbnWithoutSeparatingDashesAndXAsCheckDigit() {
    assertThat(isbnVerifier.isValid("359821507X")).isTrue();
  }

  @Test
  public void isbnWithoutCheckDigitAndDashes() {
    assertThat(isbnVerifier.isValid("359821507")).isFalse();
  }

  @Test
  public void tooLongIsbnAndNoDashes() {
    assertThat(isbnVerifier.isValid("3598215078X")).isFalse();
  }

  @Test
  public void tooShortIsbn() {
    assertThat(isbnVerifier.isValid("00")).isFalse();
  }

  @Test
  public void isbnWithoutCheckDigit() {
    assertThat(isbnVerifier.isValid("3-598-21507")).isFalse();
  }

  @Test
  public void checkDigitOfXShouldNotBeUsedForZero() {
    assertThat(isbnVerifier.isValid("3-598-21515-X")).isFalse();
  }

  @Test
  public void emptyIsbn() {
    assertThat(isbnVerifier.isValid("")).isFalse();
  }

  @Test
  public void inputIsNineCharacters() {
    assertThat(isbnVerifier.isValid("134456729")).isFalse();
  }

  @Test
  public void invalidCharactersAreNotIgnored() {
    assertThat(isbnVerifier.isValid("3132P34035")).isFalse();
  }

  @Test
  public void inputIsTooLongButContainsAValidIsbn() {
    assertThat(isbnVerifier.isValid("98245726788")).isFalse();
  }
}
