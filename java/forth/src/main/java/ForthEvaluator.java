import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ForthEvaluator {
  List<Integer> evaluateProgram(List<String> commands) {
    List<Integer> stack = new ArrayList<>();
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
            stack.add(number);
          } else if (token.equals("+")) {
            checkStackSize(stack, "Addition", 2);

            int operand2 = stack.removeLast();
            int operand1 = stack.removeLast();
            stack.add(operand1 + operand2);
          } else if (token.equals("-")) {
            checkStackSize(stack, "Subtraction", 2);

            int operand2 = stack.removeLast();
            int operand1 = stack.removeLast();
            stack.add(operand1 - operand2);
          } else if (token.equals("*")) {
            checkStackSize(stack, "Multiplication", 2);

            int operand2 = stack.removeLast();
            int operand1 = stack.removeLast();
            stack.add(operand1 * operand2);
          } else if (token.equals("/")) {
            checkStackSize(stack, "Division", 2);

            int operand2 = stack.removeLast();
            int operand1 = stack.removeLast();

            if (operand2 == 0) {
              throw new IllegalArgumentException("Division by 0 is not allowed");
            }

            stack.add(operand1 / operand2);
          } else if (token.equals("dup")) {
            checkStackSize(stack, "Duplicating", 1);

            stack.add(stack.getLast());
          } else if (token.equals("drop")) {
            checkStackSize(stack, "Dropping", 1);

            stack.removeLast();
          } else if (token.equals("swap")) {
            checkStackSize(stack, "Swapping", 2);

            int value2 = stack.removeLast();
            int value1 = stack.removeLast();
            stack.add(value2);
            stack.add(value1);
          } else if (token.equals("over")) {
            checkStackSize(stack, "Overing", 2);

            stack.add(stack.get(stack.size() - 2));
          } else {
            throw new IllegalArgumentException(
                "No definition available for operator \"%s\"".formatted(token));
          }
        }
      }
    }

    return stack;
  }

  void checkStackSize(List<Integer> stack, String operation, int minSize) {
    if (stack.size() < minSize) {
      throw new IllegalArgumentException(
          "%s requires that the stack contain at least %d value%s"
              .formatted(operation, minSize, (minSize == 1) ? "" : "s"));
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
