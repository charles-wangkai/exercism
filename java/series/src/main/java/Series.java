import java.util.List;
import java.util.stream.IntStream;

public class Series {
  String s;

  Series(String s) {
    if (s.isEmpty()) {
      throw new IllegalArgumentException("series cannot be empty");
    }

    this.s = s;
  }

  List<String> slices(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("slice length cannot be negative or zero");
    }
    if (length > s.length()) {
      throw new IllegalArgumentException("slice length cannot be greater than series length");
    }

    return IntStream.rangeClosed(0, s.length() - length)
        .mapToObj(i -> s.substring(i, i + length))
        .toList();
  }
}
