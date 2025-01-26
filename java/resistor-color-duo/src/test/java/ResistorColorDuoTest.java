import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResistorColorDuoTest {
  private ResistorColorDuo resistorColorDuo;

  @BeforeEach
  public void setup() {
    resistorColorDuo = new ResistorColorDuo();
  }

  @Test
  public void testBrownAndBlack() {
    assertThat(resistorColorDuo.value(new String[] {"brown", "black"})).isEqualTo(10);
  }

  @Test
  public void testBlueAndGrey() {
    assertThat(resistorColorDuo.value(new String[] {"blue", "grey"})).isEqualTo(68);
  }

  @Test
  public void testYellowAndViolet() {
    assertThat(resistorColorDuo.value(new String[] {"yellow", "violet"})).isEqualTo(47);
  }

  @Test
  public void testOrangeAndOrange() {
    assertThat(resistorColorDuo.value(new String[] {"orange", "orange"})).isEqualTo(33);
  }

  @Test
  public void testWhiteAndRed() {
    assertThat(resistorColorDuo.value(new String[] {"white", "red"})).isEqualTo(92);
  }

  @Test
  public void testBlackAndBrownOneDigit() {
    assertThat(resistorColorDuo.value(new String[] {"black", "brown"})).isEqualTo(1);
  }

  @Test
  public void testIgnoreAdditionalColors() {
    assertThat(resistorColorDuo.value(new String[] {"green", "brown", "orange"})).isEqualTo(51);
  }
}
