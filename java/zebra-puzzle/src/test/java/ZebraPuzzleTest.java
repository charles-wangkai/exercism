import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ZebraPuzzleTest {

  @Test
  public void residentWhoDrinksWater() {
    ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
    assertThat(zebraPuzzle.getWaterDrinker()).isEqualTo("Norwegian");
  }

  @Test
  public void residentWhoOwnsZebra() {
    ZebraPuzzle zebraPuzzle = new ZebraPuzzle();
    assertThat(zebraPuzzle.getZebraOwner()).isEqualTo("Japanese");
  }
}
