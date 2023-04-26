import java.util.Comparator;
import java.util.List;

class HighScores {
  List<Integer> highScores;

  public HighScores(List<Integer> highScores) {
    this.highScores = highScores;
  }

  List<Integer> scores() {
    return highScores;
  }

  Integer latest() {
    return highScores.get(highScores.size() - 1);
  }

  Integer personalBest() {
    return highScores.stream().max(Comparator.naturalOrder()).get();
  }

  List<Integer> personalTopThree() {
    return highScores.stream().sorted(Comparator.reverseOrder()).limit(3).toList();
  }
}
