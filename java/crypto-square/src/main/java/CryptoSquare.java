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
    StringBuilder cipherText = new StringBuilder();
    int squareSize = getSquareSize();
    List<String> segments = getPlaintextSegments();
    for (int i = 0; i < squareSize; ++i) {
      if (i != 0) {
        cipherText.append(" ");
      }

      for (String segment : segments) {
        if (i < segment.length()) {
          cipherText.append(segment.charAt(i));
        } else {
          cipherText.append(" ");
        }
      }
    }

    return cipherText.toString();
  }

  String getNormalizedCipherText() {
    StringBuilder normalizedCipherText = new StringBuilder();
    int squareSize = getSquareSize();
    List<String> segments = getPlaintextSegments();
    for (int i = 0; i < squareSize; ++i) {
      if (!normalizedCipherText.isEmpty()) {
        normalizedCipherText.append(" ");
      }

      for (String segment : segments) {
        if (i < segment.length()) {
          normalizedCipherText.append(segment.charAt(i));
        }
      }
    }

    return normalizedCipherText.toString();
  }
}
