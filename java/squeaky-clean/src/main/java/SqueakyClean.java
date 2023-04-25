import java.util.stream.Collectors;
import java.util.stream.IntStream;

class SqueakyClean {
  static String clean(String identifier) {
    return IntStream.range(0, identifier.length())
        .mapToObj(
            i -> {
              char c = identifier.charAt(i);
              if (c == ' ') {
                return "_";
              }
              if (Character.isISOControl(c)) {
                return "CTRL";
              }
              if (i != 0 && identifier.charAt(i - 1) == '-') {
                return String.valueOf(Character.toUpperCase(c));
              }

              return String.valueOf(c);
            })
        .collect(Collectors.joining())
        .chars()
        .filter(c -> (c == '_' || Character.isLetter(c)) && !(c >= 'α' && c <= 'ω'))
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
