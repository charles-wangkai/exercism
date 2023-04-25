public class IsogramChecker {
  boolean isIsogram(String s) {
    s = s.toLowerCase().replace(" ", "").replace("-", "");

    return s.chars().distinct().count() == s.length();
  }
}
