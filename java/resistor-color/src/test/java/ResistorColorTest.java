import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ResistorColorTest {

  private ResistorColor resistorColor;

  @Before
  public void setup() {
    resistorColor = new ResistorColor();
  }

  @Test
  public void testBlackColorCode() {
    assertThat(resistorColor.colorCode("black")).isEqualTo(0);
  }

  @Test
  public void testWhiteColorCode() {
    assertThat(resistorColor.colorCode("white")).isEqualTo(9);
  }

  @Test
  public void testOrangeColorCode() {
    assertThat(resistorColor.colorCode("orange")).isEqualTo(3);
  }

  @Test
  public void testColors() {
    assertThat(resistorColor.colors())
        .containsExactly(
            "black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey",
            "white");
  }
}
