import java.util.List;
import java.util.stream.Collectors;

public class Anagram {
	String s;

	Anagram(String s) {
		this.s = s;
	}

	String generateKey(String s) {
		return s.toLowerCase().chars().sorted().mapToObj(ch -> (char) ch)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	List<String> match(List<String> candidates) {
		return candidates.stream()
				.filter(candidate -> !candidate.equalsIgnoreCase(s) && generateKey(candidate).equals(generateKey(s)))
				.collect(Collectors.toList());
	}
}