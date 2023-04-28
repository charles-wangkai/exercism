import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RailFenceCipher {
  int rails;

  RailFenceCipher(int rails) {
    this.rails = rails;
  }

  private List<Integer>[] getFencePattern(int messageSize) {
    @SuppressWarnings("unchecked")
    List<Integer>[] pattern = new List[rails];
    for (int i = 0; i < pattern.length; ++i) {
      pattern[i] = new ArrayList<>();
    }

    int r = 0;
    int rOffset = 1;
    for (int c = 0; c < messageSize; ++c) {
      pattern[r].add(c);

      r += rOffset;
      if (!(r >= 0 && r < pattern.length)) {
        rOffset *= -1;
        r += rOffset * 2;
      }
    }

    return pattern;
  }

  String getEncryptedData(String message) {
    List<Integer>[] pattern = getFencePattern(message.length());

    return Arrays.stream(pattern)
        .map(
            line ->
                line.stream()
                    .map(message::charAt)
                    .map(String::valueOf)
                    .collect(Collectors.joining()))
        .collect(Collectors.joining());
  }

  String getDecryptedData(String encodedMessage) {
    List<Integer>[] pattern = getFencePattern(encodedMessage.length());

    char[] result = new char[encodedMessage.length()];
    int index = 0;
    for (List<Integer> line : pattern) {
      for (int c : line) {
        result[c] = encodedMessage.charAt(index);
        ++index;
      }
    }

    return new String(result);
  }
}
