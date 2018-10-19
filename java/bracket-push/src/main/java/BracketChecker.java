import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BracketChecker {
	String s;

	BracketChecker(String s) {
		this.s = s;
	}

	boolean areBracketsMatchedAndNestedCorrectly() {
		Stack<Character> stack = new Stack<Character>();
		for (int ch : s.chars().filter(ch -> "()[]{}".indexOf(ch) >= 0).collect(ArrayList<Integer>::new, List::add,
				List::addAll)) {
			if (ch == '(' || ch == '[' || ch == '{') {
				stack.push((char) ch);
			} else if (stack.empty() || (ch == ')' && stack.peek() != '(') || (ch == ']' && stack.peek() != '[')
					|| (ch == '}' && stack.peek() != '{')) {
				return false;
			} else {
				stack.pop();
			}
		}
		return stack.empty();
	}
}