import java.util.HashMap;
import java.util.Map;

public class WordCount {
  Map<String, Integer> phrase(String s) {
    Map<String, Integer> wordToCount = new HashMap<>();
    for (String word : s.split("([,.:!&@$%^]|\\s)+")) {
      word = word.toLowerCase();
      if (word.startsWith("'")) {
        word = word.substring(1);
      }
      if (word.endsWith("'")) {
        word = word.substring(0, word.length() - 1);
      }

      if (!word.isEmpty()) {
        wordToCount.put(word, wordToCount.getOrDefault(word, 0) + 1);
      }
    }

    return wordToCount;
  }
}
