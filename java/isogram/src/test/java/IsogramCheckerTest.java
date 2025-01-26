import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IsogramCheckerTest {
  private IsogramChecker isogramChecker;

  @BeforeEach
  public void setUp() {
    isogramChecker = new IsogramChecker();
  }

  @Test
  public void testEmptyString() {
    assertThat(isogramChecker.isIsogram("")).isTrue();
  }

  @Test
  public void testLowercaseIsogram() {
    assertThat(isogramChecker.isIsogram("isogram")).isTrue();
  }

  @Test
  public void testNotIsogram() {
    assertThat(isogramChecker.isIsogram("eleven")).isFalse();
  }

  @Test
  public void testDuplicateEndAlphabet() {
    assertThat(isogramChecker.isIsogram("zzyzx")).isFalse();
  }

  @Test
  public void testMediumLongIsogram() {
    assertThat(isogramChecker.isIsogram("subdermatoglyphic")).isTrue();
  }

  @Test
  public void testCaseInsensitive() {
    assertThat(isogramChecker.isIsogram("Alphabet")).isFalse();
  }

  @Test
  public void testDuplicateMixedCase() {
    assertThat(isogramChecker.isIsogram("alphAbet")).isFalse();
  }

  @Test
  public void testIsogramWithHyphen() {
    assertThat(isogramChecker.isIsogram("thumbscrew-japingly")).isTrue();
  }

  @Test
  public void testIsogramWithDuplicatedCharAfterHyphen() {
    assertThat(isogramChecker.isIsogram("thumbscrew-jappingly")).isFalse();
  }

  @Test
  public void testIsogramWithDuplicatedHyphen() {
    assertThat(isogramChecker.isIsogram("six-year-old")).isTrue();
  }

  @Test
  public void testMadeUpNameThatIsAnIsogram() {
    assertThat(isogramChecker.isIsogram("Emily Jung Schwartzkopf")).isTrue();
  }

  @Test
  public void testDuplicatedCharacterInTheMiddleIsNotIsogram() {
    assertThat(isogramChecker.isIsogram("accentor")).isFalse();
  }

  @Test
  public void testSameFirstAndLast() {
    assertThat(new IsogramChecker().isIsogram("angola")).isFalse();
  }

  @Test
  public void testDuplicatedCharacterAndTwoHyphens() {
    assertThat(new IsogramChecker().isIsogram("up-to-date")).isFalse();
  }
}
