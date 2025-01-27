public class Bob {
  String hey(String s) {
    if (s.chars().filter(c -> !Character.isWhitespace(c)).count() == 0) {
      return "Fine. Be that way!";
    }
    if (s.chars().filter(Character::isLetter).count() != 0
        && s.chars().filter(Character::isLetter).allMatch(Character::isUpperCase)) {
      return isQuestion(s) ? "Calm down, I know what I'm doing!" : "Whoa, chill out!";
    }
    if (isQuestion(s)) {
      return "Sure.";
    }

    return "Whatever.";
  }

  boolean isQuestion(String s) {
    return s.trim().endsWith("?");
  }
}
