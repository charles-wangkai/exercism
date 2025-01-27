import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BracketChecker {
  String s;

  BracketChecker(String s) {
    this.s = s;
  }

  boolean areBracketsMatchedAndNestedCorrectly() {
    List<Character> brackets =
        s.chars().filter(c -> "()[]{}".indexOf(c) != -1).mapToObj(c -> (char) c).toList();

    Deque<Character> stack = new ArrayDeque<>();
    for (char bracket : brackets) {
      if (bracket == '(' || bracket == '[' || bracket == '{') {
        stack.push(bracket);
      } else if (stack.isEmpty()
          || (bracket == ')' && stack.peek() != '(')
          || (bracket == ']' && stack.peek() != '[')
          || (bracket == '}' && stack.peek() != '{')) {
        return false;
      } else {
        stack.pop();
      }
    }

    return stack.isEmpty();
  }
}
