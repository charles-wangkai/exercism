public class Twofer {
  public String twofer(String name) {
    return "One for %s, one for me.".formatted((name == null) ? "you" : name);
  }
}
