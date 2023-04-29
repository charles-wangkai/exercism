import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cipher {
  static Random random = new Random();

  String key;

  Cipher() {
    key =
        IntStream.range(0, 100)
            .mapToObj(i -> (char) (random.nextInt(26) + 'a'))
            .map(String::valueOf)
            .collect(Collectors.joining());
  }

  Cipher(String key) {
    if (key.isEmpty() || !key.chars().allMatch(Character::isLowerCase)) {
      throw new IllegalArgumentException();
    }

    this.key = key;
  }

  String getKey() {
    return key;
  }

  String encode(String plainText) {
    return IntStream.range(0, plainText.length())
        .mapToObj(
            i ->
                (char)
                    ((plainText.charAt(i) - 'a' + key.charAt(i % key.length()) - 'a') % 26 + 'a'))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }

  String decode(String cipherText) {
    return IntStream.range(0, cipherText.length())
        .mapToObj(
            i ->
                (char)
                    (Math.floorMod(cipherText.charAt(i) - key.charAt(i % key.length()), 26) + 'a'))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
