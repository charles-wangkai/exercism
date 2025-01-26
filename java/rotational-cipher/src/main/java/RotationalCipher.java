import java.util.stream.Collectors;

public class RotationalCipher {
  private int key;

  public RotationalCipher(int key) {
    this.key = key;
  }

  public String rotate(String s) {
    return s.chars()
        .mapToObj(c -> rotate((char) c))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }

  private char rotate(char c) {
    if (Character.isLowerCase(c)) {
      return (char) ((c - 'a' + key) % 26 + 'a');
    }
    if (Character.isUpperCase(c)) {
      return (char) ((c - 'A' + key) % 26 + 'A');
    }

    return (char) c;
  }
}
