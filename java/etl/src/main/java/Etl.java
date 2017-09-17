import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Etl {
	public Map<String, Integer> transform(Map<Integer, List<String>> old) {
		Map<String, Integer> letter2score = new HashMap<String, Integer>();
		for (Entry<Integer, List<String>> entry : old.entrySet()) {
			int score = entry.getKey();
			for (String letter : entry.getValue()) {
				letter2score.put(letter.toLowerCase(), score);
			}
		}
		return letter2score;
	}
}
