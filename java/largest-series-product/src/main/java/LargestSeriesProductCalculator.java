import java.util.stream.IntStream;

public class LargestSeriesProductCalculator {
  String s;

  LargestSeriesProductCalculator(String s) {
    if (s == null) {
      throw new IllegalArgumentException("String to search must be non-null.");
    }
    if (!s.chars().allMatch(Character::isDigit)) {
      throw new IllegalArgumentException("String to search may only contain digits.");
    }

    this.s = s;
  }

  long calculateLargestProductForSeriesLength(int length) {
    if (length < 0) {
      throw new IllegalArgumentException("Series length must be non-negative.");
    }
    if (length > s.length()) {
      throw new IllegalArgumentException(
          "Series length must be less than or equal to the length of the string to search.");
    }

    return IntStream.rangeClosed(0, s.length() - length)
        .mapToLong(
            i ->
                IntStream.range(i, i + length)
                    .map(j -> s.charAt(j) - '0')
                    .asLongStream()
                    .reduce(1L, (acc, x) -> acc * x))
        .max()
        .getAsLong();
  }
}
