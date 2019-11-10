import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class PalindromeCalculator {
	SortedMap<Long, List<List<Integer>>> getPalindromeProductsWithFactors(int lower, int upper) {
		if (lower > upper) {
			throw new IllegalArgumentException("invalid input: min must be <= max");
		}

		SortedMap<Long, List<List<Integer>>> productToFactorizations = new TreeMap<Long, List<List<Integer>>>();
		for (int i = lower; i <= upper; i++) {
			for (int j = i; j <= upper; j++) {
				long product = (long) i * j;
				if (isPalindrome(String.valueOf(product))) {
					if (!productToFactorizations.containsKey(product)) {
						productToFactorizations.put(product, new ArrayList<List<Integer>>());
					}

					productToFactorizations.get(product).add(Arrays.asList(i, j));
				}
			}
		}

		return productToFactorizations;
	}

	boolean isPalindrome(String s) {
		return new StringBuilder(s).reverse().toString().equals(s);
	}
}