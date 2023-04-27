import java.util.Arrays;
import java.util.stream.Collectors;

class Acronym {
  String phrase;

  Acronym(String phrase) {
    this.phrase = phrase;
  }

  String get() {
    return Arrays.stream(phrase.split("( |-|_)+"))
        .map(word -> word.charAt(0))
        .map(Character::toUpperCase)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
