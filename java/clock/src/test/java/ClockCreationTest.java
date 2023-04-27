import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ClockCreationTest {

  @Test
  public void canPrintTimeOnTheHour() {
    assertThat(new Clock(8, 0).toString()).isEqualTo("08:00");
  }

  @Test
  public void canPrintTimeWithMinutes() {
    assertThat(new Clock(11, 9).toString()).isEqualTo("11:09");
  }

  @Test
  public void midnightPrintsAsZero() {
    assertThat(new Clock(24, 0).toString()).isEqualTo("00:00");
  }

  @Test
  public void hourRollsOver() {
    assertThat(new Clock(25, 0).toString()).isEqualTo("01:00");
  }

  @Test
  public void hourRollsOverContinuously() {
    assertThat(new Clock(100, 0).toString()).isEqualTo("04:00");
  }

  @Test
  public void sixtyMinutesIsNextHour() {
    assertThat(new Clock(1, 60).toString()).isEqualTo("02:00");
  }

  @Test
  public void minutesRollOver() {
    assertThat(new Clock(0, 160).toString()).isEqualTo("02:40");
  }

  @Test
  public void minutesRollOverContinuously() {
    assertThat(new Clock(0, 1723).toString()).isEqualTo("04:43");
  }

  @Test
  public void hourAndMinutesRollOver() {
    assertThat(new Clock(25, 160).toString()).isEqualTo("03:40");
  }

  @Test
  public void hourAndMinutesRollOverContinuously() {
    assertThat(new Clock(201, 3001).toString()).isEqualTo("11:01");
  }

  @Test
  public void hourAndMinutesRollOverToExactlyMidnight() {
    assertThat(new Clock(72, 8640).toString()).isEqualTo("00:00");
  }

  @Test
  public void negativeHour() {
    assertThat(new Clock(-1, 15).toString()).isEqualTo("23:15");
  }

  @Test
  public void negativeHourRollsOver() {
    assertThat(new Clock(-25, 0).toString()).isEqualTo("23:00");
  }

  @Test
  public void negativeHourRollsOverContinuously() {
    assertThat(new Clock(-91, 0).toString()).isEqualTo("05:00");
  }

  @Test
  public void negativeMinutes() {
    assertThat(new Clock(1, -40).toString()).isEqualTo("00:20");
  }

  @Test
  public void negativeMinutesRollOver() {
    assertThat(new Clock(1, -160).toString()).isEqualTo("22:20");
  }

  @Test
  public void negativeMinutesRollOverContinuously() {
    assertThat(new Clock(1, -4820).toString()).isEqualTo("16:40");
  }

  @Test
  public void negativeSixtyMinutesIsPreviousHour() {
    assertThat(new Clock(2, -60).toString()).isEqualTo("01:00");
  }

  @Test
  public void negativeHourAndMinutesBothRollOver() {
    assertThat(new Clock(-25, -160).toString()).isEqualTo("20:20");
  }

  @Test
  public void negativeHourAndMinutesBothRollOverContinuously() {
    assertThat(new Clock(-121, -5810).toString()).isEqualTo("22:10");
  }
}
