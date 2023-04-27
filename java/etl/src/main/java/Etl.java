import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Etl {
  public Map<String, Integer> transform(Map<Integer, List<String>> old) {
    Map<String, Integer> letterToScore = new HashMap<>();
    for (int score : old.keySet()) {
      for (String letter : old.get(score)) {
        letterToScore.put(letter.toLowerCase(), score);
      }
    }

    return letterToScore;
  }
}
