import java.util.List;
import java.util.stream.Collectors;

public class Atbash {
  String encode(String plaintext) {
    List<Character> letters =
        plaintext
            .toLowerCase()
            .chars()
            .filter(Character::isLetterOrDigit)
            .mapToObj(c -> (char) (Character.isDigit(c) ? c : ('a' + 'z' - c)))
            .toList();

    StringBuilder ciphertext = new StringBuilder();
    for (int i = 0; i < letters.size(); ++i) {
      if (i != 0 && i % 5 == 0) {
        ciphertext.append(" ");
      }
      ciphertext.append(letters.get(i));
    }

    return ciphertext.toString();
  }

  String decode(String ciphertext) {
    return ciphertext
        .replace(" ", "")
        .chars()
        .mapToObj(c -> (char) (Character.isDigit(c) ? c : ('a' + 'z' - c)))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
