import java.util.List;
import java.util.Stack;

public class BracketChecker {
  String s;

  BracketChecker(String s) {
    this.s = s;
  }

  boolean areBracketsMatchedAndNestedCorrectly() {
    List<Character> brackets =
        s.chars().filter(c -> "()[]{}".indexOf(c) >= 0).mapToObj(c -> (char) c).toList();
    Stack<Character> stack = new Stack<>();
    for (char bracket : brackets) {
      if (bracket == '(' || bracket == '[' || bracket == '{') {
        stack.push(bracket);
      } else if (stack.empty()
          || (bracket == ')' && stack.peek() != '(')
          || (bracket == ']' && stack.peek() != '[')
          || (bracket == '}' && stack.peek() != '{')) {
        return false;
      } else {
        stack.pop();
      }
    }

    return stack.empty();
  }
}
