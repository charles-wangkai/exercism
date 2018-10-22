import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

public class PalindromeCalculator {
	SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int lower, int upper) {
		if (lower > upper) {
			throw new IllegalArgumentException(String.format("invalid input: min is %d and max is %d", lower, upper));
		}

		SortedMap<Long, List<List<Integer>>> product2factorizations = new TreeMap<Long, List<List<Integer>>>();
		for (int i = lower; i <= upper; i++) {
			for (int j = i; j <= upper; j++) {
				long product = (long) i * j;
				if (isPalindrome(String.valueOf(product))) {
					if (!product2factorizations.containsKey(product)) {
						product2factorizations.put(product, new ArrayList<List<Integer>>());
					}

					product2factorizations.get(product).add(Arrays.asList(i, j));
				}
			}
		}

		if (product2factorizations.isEmpty()) {
			throw new NoSuchElementException(
					String.format("no palindrome with factors in the range %d to %d", lower, upper));
		}

		return product2factorizations;
	}

	boolean isPalindrome(String s) {
		return new StringBuilder(s).reverse().toString().equals(s);
	}
}