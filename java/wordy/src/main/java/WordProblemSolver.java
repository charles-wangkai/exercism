import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordProblemSolver {
	int solve(String problem) {
		int[] values = Arrays.stream(problem.split("[^0-9+-]+")).filter(field -> field.length() > 0)
				.mapToInt(Integer::parseInt).toArray();

		if (values.length != 2 && values.length != 3) {
			throw new IllegalArgumentException("I'm sorry, I don't understand the question!");
		}

		List<String> parts = Arrays.stream(problem.split("[0-9+-]+")).collect(Collectors.toList());

		if (values.length == 2) {
			return compute(values[0], parts.get(1), values[1]);
		} else {
			return compute(compute(values[0], parts.get(1), values[1]), parts.get(2), values[2]);
		}
	}

	int compute(int x, String operation, int y) {
		if (operation.contains("plus")) {
			return x + y;
		} else if (operation.contains("minus")) {
			return x - y;
		} else if (operation.contains("multiplied")) {
			return x * y;
		} else {
			return x / y;
		}
	}
}
