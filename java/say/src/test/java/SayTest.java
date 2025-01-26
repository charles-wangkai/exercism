import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SayTest {

  private Say say = new Say();

  @Test
  public void zero() {
    assertThat(say.say(0)).isEqualTo("zero");
  }

  @Test
  public void one() {
    assertThat(say.say(1)).isEqualTo("one");
  }

  @Test
  public void fourteen() {
    assertThat(say.say(14)).isEqualTo("fourteen");
  }

  @Test
  public void twenty() {
    assertThat(say.say(20)).isEqualTo("twenty");
  }

  @Test
  public void twentyTwo() {
    assertThat(say.say(22)).isEqualTo("twenty-two");
  }

  @Test
  public void thirty() {
    assertThat(say.say(30)).isEqualTo("thirty");
  }

  @Test
  public void ninetyNine() {
    assertThat(say.say(99)).isEqualTo("ninety-nine");
  }

  @Test
  public void oneHundred() {
    assertThat(say.say(100)).isEqualTo("one hundred");
  }

  @Test
  public void oneHundredTwentyThree() {
    assertThat(say.say(123)).isEqualTo("one hundred twenty-three");
  }

  @Test
  public void twoHundred() {
    assertThat(say.say(200)).isEqualTo("two hundred");
  }

  @Test
  public void nineHundredNinetyNine() {
    assertThat(say.say(999)).isEqualTo("nine hundred ninety-nine");
  }

  @Test
  public void oneThousand() {
    assertThat(say.say(1_000)).isEqualTo("one thousand");
  }

  @Test
  public void oneThousandTwoHundredThirtyFour() {
    assertThat(say.say(1_234)).isEqualTo("one thousand two hundred thirty-four");
  }

  @Test
  public void oneMillion() {
    assertThat(say.say(1_000_000)).isEqualTo("one million");
  }

  @Test
  public void oneMillionTwoThousandThreeHundredFortyFive() {
    assertThat(say.say(1_002_345)).isEqualTo("one million two thousand three hundred forty-five");
  }

  @Test
  public void oneBillion() {
    assertThat(say.say(1_000_000_000)).isEqualTo("one billion");
  }

  @Test
  public void
      nineHundredEightySevenBillionSixHundredFiftyFourThreeHundredTwentyOneThousandOneHundredTwentyThree() {
    assertThat(say.say(987_654_321_123L))
        .isEqualTo(
            "nine hundred eighty-seven billion six hundred fifty-four million"
                + " three hundred twenty-one thousand one hundred twenty-three");
  }

  @Test
  public void illegalNegativeNumber() {
    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> say.say(-1));
  }

  @Test
  public void illegalTooBigNumber() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> say.say(1_000_000_000_000L));
  }
}
