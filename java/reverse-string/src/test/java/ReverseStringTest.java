import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ReverseStringTest {

  @Test
  public void testAnEmptyString() {
    assertThat(new ReverseString().reverse("")).isEqualTo("");
  }

  @Test
  public void testAWord() {
    assertThat(new ReverseString().reverse("robot")).isEqualTo("tobor");
  }

  @Test
  public void testACapitalizedWord() {
    assertThat(new ReverseString().reverse("Ramen")).isEqualTo("nemaR");
  }

  @Test
  public void testASentenceWithPunctuation() {
    assertThat(new ReverseString().reverse("I'm hungry!")).isEqualTo("!yrgnuh m'I");
  }

  @Test
  public void testAPalindrome() {
    assertThat(new ReverseString().reverse("racecar")).isEqualTo("racecar");
  }

  @Test
  public void testAnEvenSizedWord() {
    assertThat(new ReverseString().reverse("drawer")).isEqualTo("reward");
  }
}
