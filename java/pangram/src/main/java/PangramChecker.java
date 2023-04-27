public class PangramChecker {
  public boolean isPangram(String input) {
    return input.toLowerCase().chars().filter(Character::isLetter).distinct().count() == 26;
  }
}
