import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelLetterFrequency {
  String[] strs;

  ParallelLetterFrequency(String[] strs) {
    this.strs = strs;
  }

  Map<Character, Integer> countLetters() {
    return Arrays.stream(strs)
        .parallel()
        .flatMapToInt(s -> s.toLowerCase().chars())
        .filter(Character::isAlphabetic)
        .boxed()
        .collect(Collectors.groupingBy(c -> (char) c.intValue(), Collectors.summingInt(c -> 1)));
  }
}
