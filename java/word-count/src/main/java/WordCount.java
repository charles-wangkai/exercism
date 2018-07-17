import java.util.HashMap;
import java.util.Map;

public class WordCount {
	Map<String, Integer> phrase(String s) {
		Map<String, Integer> word2count = new HashMap<>();
		for (String word : s.split("([,.:!&@$%^]|\\s)+")) {
			if (word.startsWith("'") && word.endsWith("'")) {
				word = word.substring(1, word.length() - 1);
			}

			if (word.isEmpty()) {
				continue;
			}

			word = word.toLowerCase();
			incrementWordCount(word2count, word);
		}
		return word2count;
	}

	void incrementWordCount(Map<String, Integer> word2count, String word) {
		word2count.put(word, word2count.getOrDefault(word, 0) + 1);
	}
}