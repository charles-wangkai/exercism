import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ScrabbleScoreTest {

  @Test
  public void testALowerCaseLetter() {
    Scrabble scrabble = new Scrabble("a");
    assertThat(scrabble.getScore()).isEqualTo(1);
  }

  @Test
  public void testAUpperCaseLetter() {
    Scrabble scrabble = new Scrabble("A");
    assertThat(scrabble.getScore()).isEqualTo(1);
  }

  @Test
  public void testAValuableLetter() {
    Scrabble scrabble = new Scrabble("f");
    assertThat(scrabble.getScore()).isEqualTo(4);
  }

  @Test
  public void testAShortWord() {
    Scrabble scrabble = new Scrabble("at");
    assertThat(scrabble.getScore()).isEqualTo(2);
  }

  @Test
  public void testAShortValuableWord() {
    Scrabble scrabble = new Scrabble("zoo");
    assertThat(scrabble.getScore()).isEqualTo(12);
  }

  @Test
  public void testAMediumWord() {
    Scrabble scrabble = new Scrabble("street");
    assertThat(scrabble.getScore()).isEqualTo(6);
  }

  @Test
  public void testAMediumValuableWord() {
    Scrabble scrabble = new Scrabble("quirky");
    assertThat(scrabble.getScore()).isEqualTo(22);
  }

  @Test
  public void testALongMixCaseWord() {
    Scrabble scrabble = new Scrabble("OxyphenButazone");
    assertThat(scrabble.getScore()).isEqualTo(41);
  }

  @Test
  public void testAEnglishLikeWord() {
    Scrabble scrabble = new Scrabble("pinata");
    assertThat(scrabble.getScore()).isEqualTo(8);
  }

  @Test
  public void testAnEmptyInput() {
    Scrabble scrabble = new Scrabble("");
    assertThat(scrabble.getScore()).isEqualTo(0);
  }

  @Test
  public void testEntireAlphabetAvailable() {
    Scrabble scrabble = new Scrabble("abcdefghijklmnopqrstuvwxyz");
    assertThat(scrabble.getScore()).isEqualTo(87);
  }
}
