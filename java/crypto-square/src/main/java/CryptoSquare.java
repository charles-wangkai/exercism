import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CryptoSquare {
  String s;

  CryptoSquare(String s) {
    this.s = s;
  }

  String getNormalizedPlaintext() {
    return s.toLowerCase()
        .chars()
        .filter(Character::isLetterOrDigit)
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }

  int getSquareSize() {
    return (int) Math.ceil(Math.sqrt(getNormalizedPlaintext().length()));
  }

  List<String> getPlaintextSegments() {
    List<String> segments = new ArrayList<>();
    String normalizedPlaintext = getNormalizedPlaintext();
    int squareSize = getSquareSize();
    for (int i = 0; i < normalizedPlaintext.length(); i += squareSize) {
      segments.add(
          normalizedPlaintext.substring(i, Math.min(i + squareSize, normalizedPlaintext.length())));
    }

    return segments;
  }

  String getCiphertext() {
    StringBuilder result = new StringBuilder();
    int squareSize = getSquareSize();
    List<String> segments = getPlaintextSegments();
    for (int i = 0; i < squareSize; ++i) {
      if (i != 0) {
        result.append(" ");
      }

      for (String segment : segments) {
        result.append((i < segment.length()) ? segment.charAt(i) : " ");
      }
    }

    return result.toString();
  }

  String getNormalizedCipherText() {
    StringBuilder result = new StringBuilder();
    int squareSize = getSquareSize();
    List<String> segments = getPlaintextSegments();
    for (int i = 0; i < squareSize; ++i) {
      if (!result.isEmpty()) {
        result.append(" ");
      }

      for (String segment : segments) {
        if (i < segment.length()) {
          result.append(segment.charAt(i));
        }
      }
    }

    return result.toString();
  }
}
