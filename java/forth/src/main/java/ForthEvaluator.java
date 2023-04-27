import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class ForthEvaluator {
  List<Integer> evaluateProgram(List<String> commands) {
    Stack<Integer> stack = new Stack<>();
    Map<String, String> nameToDefinition = new HashMap<>();
    for (String command : commands) {
      command = command.toLowerCase();

      if (command.startsWith(": ")) {
        List<String> tokens = tokenize(command.substring(2, command.length() - 2));
        String name = tokens.get(0);
        if (isNumber(name)) {
          throw new IllegalArgumentException("Cannot redefine numbers");
        }

        String definition =
            replace(String.join(" ", tokens.subList(1, tokens.size())), nameToDefinition);
        nameToDefinition.put(name, definition);
      } else {
        for (String token : tokenize(replace(command, nameToDefinition))) {
          if (isNumber(token)) {
            int number = Integer.parseInt(token);
            stack.push(number);
          } else if (token.equals("+")) {
            checkStackSize(stack, "Addition", 2);

            int number1 = stack.pop();
            int number2 = stack.pop();
            stack.push(number2 + number1);
          } else if (token.equals("-")) {
            checkStackSize(stack, "Subtraction", 2);

            int number1 = stack.pop();
            int number2 = stack.pop();
            stack.push(number2 - number1);
          } else if (token.equals("*")) {
            checkStackSize(stack, "Multiplication", 2);

            int number1 = stack.pop();
            int number2 = stack.pop();
            stack.push(number2 * number1);
          } else if (token.equals("/")) {
            checkStackSize(stack, "Division", 2);

            int number1 = stack.pop();
            int number2 = stack.pop();

            if (number1 == 0) {
              throw new IllegalArgumentException("Division by 0 is not allowed");
            }

            stack.push(number2 / number1);
          } else if (token.equals("dup")) {
            checkStackSize(stack, "Duplicating", 1);

            stack.push(stack.peek());
          } else if (token.equals("drop")) {
            checkStackSize(stack, "Dropping", 1);

            stack.pop();
          } else if (token.equals("swap")) {
            checkStackSize(stack, "Swapping", 2);

            int number1 = stack.pop();
            int number2 = stack.pop();
            stack.push(number1);
            stack.push(number2);
          } else if (token.equals("over")) {
            checkStackSize(stack, "Overing", 2);

            stack.push(stack.get(stack.size() - 2));
          } else {
            throw new IllegalArgumentException(
                String.format("No definition available for operator \"%s\"", token));
          }
        }
      }
    }

    return stack;
  }

  void checkStackSize(Stack<Integer> stack, String operation, int minSize) {
    if (stack.size() < minSize) {
      throw new IllegalArgumentException(
          String.format(
              "%s requires that the stack contain at least %d value%s",
              operation, minSize, (minSize == 1) ? "" : "s"));
    }
  }

  String replace(String command, Map<String, String> nameToDefinition) {
    return tokenize(command).stream()
        .map(token -> nameToDefinition.getOrDefault(token, token))
        .collect(Collectors.joining(" "));
  }

  List<String> tokenize(String s) {
    return Arrays.stream(s.split(" +")).filter(token -> !token.isEmpty()).toList();
  }

  boolean isNumber(String s) {
    try {
      Integer.parseInt(s);

      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
