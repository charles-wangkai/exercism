import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EliudsEggsTest {
  @Test
  @DisplayName("0 eggs")
  public void test0Eggs() {
    assertThat(new EliudsEggs().eggCount(0)).isEqualTo(0);
  }

  @Test
  @DisplayName("1 egg")
  public void test1Egg() {
    assertThat(new EliudsEggs().eggCount(16)).isEqualTo(1);
  }

  @Test
  @DisplayName("4 eggs")
  public void test4Eggs() {
    assertThat(new EliudsEggs().eggCount(89)).isEqualTo(4);
  }

  @Test
  @DisplayName("13 eggs")
  public void test13Eggs() {
    assertThat(new EliudsEggs().eggCount(2000000000)).isEqualTo(13);
  }
}
