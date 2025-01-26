import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PangramCheckerTest {

  private PangramChecker pangramChecker;

  @BeforeEach
  public void setup() {
    pangramChecker = new PangramChecker();
  }

  @Test
  public void emptySentenceIsNotPangram() {
    assertThat(pangramChecker.isPangram("")).isFalse();
  }

  @Test
  public void perfectLowerCasePhraseIsPangram() {
    assertThat(pangramChecker.isPangram("abcdefghijklmnopqrstuvwxyz")).isTrue();
  }

  @Test
  public void phraseWithOnlyLowerCaseIsPangram() {
    assertThat(pangramChecker.isPangram("the quick brown fox jumps over the lazy dog")).isTrue();
  }

  @Test
  public void phraseMissingCharacterXIsNotPangram() {
    assertThat(
            pangramChecker.isPangram("a quick movement of the enemy will jeopardize five gunboats"))
        .isFalse();
  }

  @Test
  public void phraseMissingCharacterHIsNotPangram() {
    assertThat(pangramChecker.isPangram("five boxing wizards jump quickly at it")).isFalse();
  }

  @Test
  public void phraseWithUnderscoresIsPangram() {
    assertThat(pangramChecker.isPangram("the_quick_brown_fox_jumps_over_the_lazy_dog")).isTrue();
  }

  @Test
  public void phraseWithNumbersIsPangram() {
    assertThat(pangramChecker.isPangram("the 1 quick brown fox jumps over the 2 lazy dogs"))
        .isTrue();
  }

  @Test
  public void phraseWithMissingLettersReplacedByNumbersIsNotPangram() {
    assertThat(pangramChecker.isPangram("7h3 qu1ck brown fox jumps ov3r 7h3 lazy dog")).isFalse();
  }

  @Test
  public void phraseWithMixedCaseAndPunctuationIsPangram() {
    assertThat(pangramChecker.isPangram("\"Five quacking Zephyrs jolt my wax bed.\"")).isTrue();
  }

  @Test
  public void caseInsensitivePhraseIsNotPangram() {
    assertThat(pangramChecker.isPangram("abcdefghijklm ABCDEFGHIJKLM")).isFalse();
  }
}
