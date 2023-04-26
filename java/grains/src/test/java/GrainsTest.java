import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import org.junit.Test;

public class GrainsTest {

  private Grains grains = new Grains();

  @Test
  public void countAtSquare1() {
    BigInteger result = grains.grainsOnSquare(1);
    assertEquals(new BigInteger("1"), result);
  }

  @Test
  public void countAtSquare2() {
    BigInteger result = grains.grainsOnSquare(2);
    assertEquals(new BigInteger("2"), result);
  }

  @Test
  public void countAtSquare3() {
    BigInteger result = grains.grainsOnSquare(3);
    assertEquals(new BigInteger("4"), result);
  }

  @Test
  public void countAtSquare4() {
    BigInteger result = grains.grainsOnSquare(4);
    assertEquals(new BigInteger("8"), result);
  }

  @Test
  public void countAtSquare16() {
    BigInteger result = grains.grainsOnSquare(16);
    assertEquals(new BigInteger("32768"), result);
  }

  @Test
  public void countAtSquare32() {
    BigInteger result = grains.grainsOnSquare(32);
    assertEquals(new BigInteger("2147483648"), result);
  }

  @Test
  public void countAtSquare64() {
    BigInteger result = grains.grainsOnSquare(64);
    assertEquals(new BigInteger("9223372036854775808"), result);
  }

  @Test
  public void errorOnNullBoardSize() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> grains.grainsOnSquare(0))
        .withMessage("square must be between 1 and 64");
  }

  @Test
  public void errorOnNegativeBoardSize() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> grains.grainsOnSquare(-1))
        .withMessage("square must be between 1 and 64");
  }

  @Test
  public void errorOnExcessiveBoardSize() {
    assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> grains.grainsOnSquare(65))
        .withMessage("square must be between 1 and 64");
  }

  @Test
  public void totalNumberOfGrainsOnABoard() {
    BigInteger total = grains.grainsOnBoard();
    assertEquals(new BigInteger("18446744073709551615"), total);
  }
}
