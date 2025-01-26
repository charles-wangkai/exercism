import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class HighScoresTest {

  @Test
  public void shouldReturnListOfScores() {
    HighScores highScores = new HighScores(Arrays.asList(30, 50, 20, 70));
    assertThat(highScores.scores()).isEqualTo(Arrays.asList(30, 50, 20, 70));
  }

  @Test
  public void shouldReturnLatestAddedScore() {
    HighScores highScores = new HighScores(Arrays.asList(100, 0, 90, 30));
    assertThat(highScores.latest()).isEqualTo(30);
  }

  @Test
  public void shouldReturnPersonalBest() {
    HighScores highScores = new HighScores(Arrays.asList(40, 100, 70));
    assertThat(highScores.personalBest()).isEqualTo(100);
  }

  @Test
  public void shouldReturnPersonalTopThreeFromListOfScores() {
    HighScores highScores =
        new HighScores(Arrays.asList(10, 30, 90, 30, 100, 20, 10, 0, 30, 40, 40, 70, 70));
    assertThat(highScores.personalTopThree()).isEqualTo(Arrays.asList(100, 90, 70));
  }

  @Test
  public void shouldReturnPersonalTopThreeSortedHighestToLowest() {
    HighScores highScores = new HighScores(Arrays.asList(20, 10, 30));
    assertThat(highScores.personalTopThree()).isEqualTo(Arrays.asList(30, 20, 10));
  }

  @Test
  public void shouldReturnPersonalTopThreeWhenThereIsATie() {
    HighScores highScores = new HighScores(Arrays.asList(40, 20, 40, 30));
    assertThat(highScores.personalTopThree()).isEqualTo(Arrays.asList(40, 40, 30));
  }

  @Test
  public void shouldReturnPersonalTopWhenThereIsLessThanThreeScores() {
    HighScores highScores = new HighScores(Arrays.asList(30, 70));
    assertThat(highScores.personalTopThree()).isEqualTo(Arrays.asList(70, 30));
  }

  @Test
  public void shouldReturnPersonalTopWhenThereIsOnlyOneScore() {
    HighScores highScores = new HighScores(Arrays.asList(40));
    assertThat(highScores.personalTopThree()).isEqualTo(Arrays.asList(40));
  }

  @Test
  public void callingLatestAfterPersonalTopThree() {
    HighScores highScores = new HighScores(Arrays.asList(70, 50, 20, 30));
    highScores.personalTopThree();
    assertThat(highScores.latest()).isEqualTo(30);
  }

  @Test
  public void callingScoresAfterPersonalTopThree() {
    HighScores highScores = new HighScores(Arrays.asList(30, 50, 20, 70));
    highScores.personalTopThree();
    assertThat(highScores.scores()).isEqualTo(Arrays.asList(30, 50, 20, 70));
  }

  @Test
  public void callingLatestAfterPersonalBest() {
    HighScores highScores = new HighScores(Arrays.asList(20, 70, 15, 25, 30));
    highScores.personalBest();
    assertThat(highScores.latest()).isEqualTo(30);
  }

  @Test
  public void callingScoresAfterPersonalBest() {
    HighScores highScores = new HighScores(Arrays.asList(20, 70, 15, 25, 30));
    highScores.personalBest();
    assertThat(highScores.scores()).isEqualTo(Arrays.asList(20, 70, 15, 25, 30));
  }
}
