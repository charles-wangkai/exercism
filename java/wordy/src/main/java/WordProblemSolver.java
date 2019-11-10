import java.util.Arrays;

public class WordProblemSolver {
	static final String EXCEPTION_MESSAGE = "I'm sorry, I don't understand the question!";

	int solve(String problem) {
		String[] fields = Arrays.stream(problem.split("What is|by|\\?| ")).filter(field -> !field.isEmpty())
				.toArray(String[]::new);

		if (fields.length % 2 == 0) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}

		int result = parseValue(fields[0]);
		for (int i = 1; i < fields.length; i += 2) {
			result = compute(result, fields[i], parseValue(fields[i + 1]));
		}

		return result;
	}

	int parseValue(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
	}

	int compute(int x, String operation, int y) {
		if (operation.equals("plus")) {
			return x + y;
		} else if (operation.equals("minus")) {
			return x - y;
		} else if (operation.equals("multiplied")) {
			return x * y;
		} else if (operation.equals("divided")) {
			return x / y;
		}

		throw new IllegalArgumentException(EXCEPTION_MESSAGE);
	}
}