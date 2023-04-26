import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DartsTest {

  Darts darts = new Darts();

  @Test
  public void missedTarget() {
    assertEquals(0, darts.score(-9, 9));
  }

  @Test
  public void onTheOuterCircle() {
    assertEquals(1, darts.score(0, 10));
  }

  @Test
  public void onTheMiddleCircle() {
    assertEquals(5, darts.score(-5, 0));
  }

  @Test
  public void onTheInnerCircle() {
    assertEquals(10, darts.score(0, -1));
  }

  @Test
  public void exactlyOnCentre() {
    assertEquals(10, darts.score(0, 0));
  }

  @Test
  public void nearTheCentre() {
    assertEquals(10, darts.score(-0.1, -0.1));
  }

  @Test
  public void justWithinTheInnerCircle() {
    assertEquals(10, darts.score(0.7, 0.7));
  }

  @Test
  public void justOutsideTheInnerCircle() {
    assertEquals(5, darts.score(0.8, -0.8));
  }

  @Test
  public void justWithinTheMiddleCircle() {
    assertEquals(5, darts.score(-3.5, 3.5));
  }

  @Test
  public void justOutsideTheMiddleCircle() {
    assertEquals(1, darts.score(-3.6, -3.6));
  }

  @Test
  public void justWithinTheOuterCircle() {
    assertEquals(1, darts.score(-7.0, 7.0));
  }

  @Test
  public void justOutsideTheOuterCircle() {
    assertEquals(0, darts.score(7.1, -7.1));
  }

  @Test
  public void asymmetricPositionBetweenTheInnerAndMiddleCircles() {
    assertEquals(5, darts.score(0.5, -4));
  }
}
