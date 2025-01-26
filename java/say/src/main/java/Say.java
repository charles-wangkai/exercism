import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Say {
  static final String[] CHUNK_NAMES = {
    "", "thousand", "million", "billion", "trillion", "quadrillion", "quintillion"
  };
  static final String[] ONE_NAMES = {
    "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
  };
  static final String[] TEN_NAMES = {
    "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
  };
  static final String[] TEEN_NAMES = {
    "eleven",
    "twelve",
    "thirteen",
    "fourteen",
    "fifteen",
    "sixteen",
    "seventeen",
    "eighteen",
    "nineteen"
  };

  public String say(long number) {
    if (number < 0 || number > 999_999_999_999L) {
      throw new IllegalArgumentException();
    }

    if (number == 0) {
      return "zero";
    }

    List<Integer> values = new ArrayList<>();
    while (number != 0) {
      values.add((int) (number % 1000));
      number /= 1000;
    }

    String[] parts =
        IntStream.range(0, values.size())
            .filter(i -> values.get(i) != 0)
            .mapToObj(
                i ->
                    sayWithinThousand(values.get(i))
                        + (CHUNK_NAMES[i].isEmpty() ? "" : " ")
                        + CHUNK_NAMES[i])
            .toArray(String[]::new);

    return IntStream.range(0, parts.length)
        .mapToObj(i -> parts[parts.length - 1 - i])
        .collect(Collectors.joining(" "));
  }

  String sayWithinThousand(int n) {
    List<String> parts = new ArrayList<>();

    if (n >= 100) {
      parts.add(ONE_NAMES[n / 100] + " hundred");
    }

    int rest = n % 100;
    if (rest != 0) {
      if (rest >= 11 && rest <= 19) {
        parts.add(TEEN_NAMES[rest - 11]);
      } else {
        StringBuilder part = new StringBuilder();

        int ten = rest / 10;
        if (ten != 0) {
          part.append(TEN_NAMES[ten]);
        }

        int one = rest % 10;
        if (one != 0) {
          if (!part.isEmpty()) {
            part.append("-");
          }
          part.append(ONE_NAMES[one]);
        }

        if (!part.isEmpty()) {
          parts.add(part.toString());
        }
      }
    }

    return String.join(" ", parts);
  }
}
