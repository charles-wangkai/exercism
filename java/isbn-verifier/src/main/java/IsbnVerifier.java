import java.util.stream.IntStream;

class IsbnVerifier {
  boolean isValid(String stringToVerify) {
    String s = stringToVerify.replace("-", "");

    return s.length() == 10
        && IntStream.range(0, 9).allMatch(i -> Character.isDigit(s.charAt(i)))
        && (s.charAt(9) == 'X' || Character.isDigit(s.charAt(9)))
        && IntStream.range(0, 10).map(i -> toNumber(s.charAt(i)) * (10 - i)).sum() % 11 == 0;
  }

  int toNumber(char c) {
    return (c == 'X') ? 10 : (c - '0');
  }
}
