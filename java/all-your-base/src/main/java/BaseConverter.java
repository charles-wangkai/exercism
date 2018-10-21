import java.util.Arrays;
import java.util.Stack;

public class BaseConverter {
	int base;
	int[] digits;

	BaseConverter(int base, int[] digits) {
		checkBase(base);

		if (Arrays.stream(digits).anyMatch(digit -> digit >= base)) {
			throw new IllegalArgumentException("All digits must be strictly less than the base.");
		}
		if (Arrays.stream(digits).anyMatch(digit -> digit < 0)) {
			throw new IllegalArgumentException("Digits may not be negative.");
		}

		this.base = base;
		this.digits = digits;
	}

	int[] convertToBase(int targetBase) {
		checkBase(targetBase);

		int number = 0;
		for (int digit : digits) {
			number = number * base + digit;
		}

		Stack<Integer> targetDigits = new Stack<Integer>();
		while (number != 0) {
			targetDigits.push(number % targetBase);
			number /= targetBase;
		}
		if (targetDigits.empty()) {
			targetDigits.push(0);
		}

		int[] result = new int[targetDigits.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = targetDigits.pop();
		}
		return result;
	}

	void checkBase(int b) {
		if (b < 2) {
			throw new IllegalArgumentException("Bases must be at least 2.");
		}
	}
}