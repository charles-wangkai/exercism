import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParallelLetterFrequency {
  String s;

  ParallelLetterFrequency(String s) {
    this.s = s;
  }

  Map<Integer, Integer> letterCounts() {
    return s.toLowerCase()
        .chars()
        .parallel()
        .filter(Character::isAlphabetic)
        .boxed()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(c -> 1)));
  }
}
