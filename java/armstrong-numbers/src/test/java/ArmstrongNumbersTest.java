import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArmstrongNumbersTest {

  private ArmstrongNumbers armstrongNumbers;

  @BeforeEach
  public void setup() {
    armstrongNumbers = new ArmstrongNumbers();
  }

  @Test
  public void zeroIsArmstrongNumber() {
    assertThat(armstrongNumbers.isArmstrongNumber(0)).isTrue();
  }

  @Test
  public void singleDigitsAreArmstrongNumbers() {
    assertThat(armstrongNumbers.isArmstrongNumber(5)).isTrue();
  }

  @Test
  public void noTwoDigitArmstrongNumbers() {
    assertThat(armstrongNumbers.isArmstrongNumber(10)).isFalse();
  }

  @Test
  public void threeDigitNumberIsArmstrongNumber() {
    assertThat(armstrongNumbers.isArmstrongNumber(153)).isTrue();
  }

  @Test
  public void threeDigitNumberIsNotArmstrongNumber() {
    assertThat(armstrongNumbers.isArmstrongNumber(100)).isFalse();
  }

  @Test
  public void fourDigitNumberIsArmstrongNumber() {
    assertThat(armstrongNumbers.isArmstrongNumber(9474)).isTrue();
  }

  @Test
  public void fourDigitNumberIsNotArmstrongNumber() {
    assertThat(armstrongNumbers.isArmstrongNumber(9475)).isFalse();
  }

  @Test
  public void sevenDigitNumberIsArmstrongNumber() {
    assertThat(armstrongNumbers.isArmstrongNumber(9926315)).isTrue();
  }

  @Test
  public void sevenDigitNumberIsNotArmstrongNumber() {
    assertThat(armstrongNumbers.isArmstrongNumber(9926314)).isFalse();
  }
}
