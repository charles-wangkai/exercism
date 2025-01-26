import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class CollatzCalculatorTest {

  private CollatzCalculator collatzCalculator = new CollatzCalculator();

  @Test
  public void testZeroStepsRequiredWhenStartingFrom1() {
    assertThat(collatzCalculator.computeStepCount(1)).isEqualTo(0);
  }

  @Test
  public void testCorrectNumberOfStepsWhenAllStepsAreDivisions() {
    assertThat(collatzCalculator.computeStepCount(16)).isEqualTo(4);
  }

  @Test
  public void testCorrectNumberOfStepsWhenBothStepTypesAreNeeded() {
    assertThat(collatzCalculator.computeStepCount(12)).isEqualTo(9);
  }

  @Test
  public void testAVeryLargeInput() {
    assertThat(collatzCalculator.computeStepCount(1000000)).isEqualTo(152);
  }

  @Test
  public void testZeroIsConsideredInvalidInput() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> collatzCalculator.computeStepCount(0))
        .withMessage("Only positive integers are allowed");
  }

  @Test
  public void testNegativeIntegerIsConsideredInvalidInput() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> collatzCalculator.computeStepCount(-15))
        .withMessage("Only positive integers are allowed");
  }
}
