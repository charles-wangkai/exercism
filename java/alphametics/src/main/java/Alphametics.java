import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Alphametics {
  List<Character> letters;
  String[] items;

  Alphametics(String puzzle) {
    letters =
        puzzle.chars().filter(Character::isAlphabetic).mapToObj(c -> (char) c).distinct().toList();
    items = puzzle.split("\\W+");
  }

  Map<Character, Integer> solve() throws UnsolvablePuzzleException {
    int[] digits = IntStream.range(0, 10).toArray();

    Map<Character, Integer> letterToDigit = search(letters, digits, 0);
    if (letterToDigit == null) {
      throw new UnsolvablePuzzleException();
    }

    return letterToDigit;
  }

  Map<Character, Integer> search(List<Character> letters, int[] digits, int index) {
    if (index == letters.size()) {
      Map<Character, Integer> letterToDigit =
          IntStream.range(0, letters.size())
              .boxed()
              .collect(Collectors.toMap(i -> letters.get(i), i -> digits[i]));

      return check(letterToDigit) ? letterToDigit : null;
    }

    for (int i = index; i < digits.length; ++i) {
      swap(digits, i, index);

      Map<Character, Integer> letterToDigit = search(letters, digits, index + 1);
      if (letterToDigit != null) {
        return letterToDigit;
      }

      swap(digits, i, index);
    }

    return null;
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }

  boolean check(Map<Character, Integer> letterToDigit) {
    long[] values = new long[items.length];
    for (int i = 0; i < values.length; ++i) {
      if (items[i].length() > 1 && letterToDigit.get(items[i].charAt(0)) == 0) {
        return false;
      }

      for (char c : items[i].toCharArray()) {
        values[i] = values[i] * 10 + letterToDigit.get(c);
      }
    }

    return IntStream.range(0, values.length - 1).mapToLong(i -> values[i]).sum()
        == values[values.length - 1];
  }
}
