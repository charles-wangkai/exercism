import java.util.HashMap;
import java.util.Map;

public class WordCount {
	Map<String, Integer> phrase(String s) {
		Map<String, Integer> word2count = new HashMap<String, Integer>();
		for (String word : s.split("\\W+")) {
			System.out.println(word);
			word = word.toLowerCase();
			word2count.put(word, word2count.getOrDefault(word, 0) + 1);
		}
		return word2count;
	}
}
