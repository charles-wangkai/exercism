import java.util.List;
import java.util.stream.Collectors;

public class Anagram {
  String s;

  Anagram(String s) {
    this.s = s;
  }

  String generateKey(String s) {
    return s.toLowerCase()
        .chars()
        .sorted()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }

  List<String> match(List<String> candidates) {
    return candidates.stream()
        .filter(
            candidate ->
                !candidate.equalsIgnoreCase(s) && generateKey(candidate).equals(generateKey(s)))
        .toList();
  }
}
