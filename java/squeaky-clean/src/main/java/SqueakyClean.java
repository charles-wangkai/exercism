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
              if (i != 0 && identifier.charAt(i - 1) == '-') {
                return String.valueOf(Character.toUpperCase(c));
              }
              if (c == '4') {
                return "a";
              }
              if (c == '3') {
                return "e";
              }
              if (c == '0') {
                return "o";
              }
              if (c == '1') {
                return "l";
              }
              if (c == '7') {
                return "t";
              }

              return String.valueOf(c);
            })
        .collect(Collectors.joining())
        .chars()
        .filter(c -> c == '_' || Character.isLetter(c))
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
