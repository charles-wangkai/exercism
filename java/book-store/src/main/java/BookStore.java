import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookStore {
	static final int PRICE = 8;
	static final double[] RATIOS = { -1, 1, 0.95, 0.9, 0.8, 0.75 };

	double calculateBasketCost(List<Integer> books) {
		int[] counts = new int[5];
		for (int book : books) {
			counts[book - 1]++;
		}

		return search(new HashMap<String, Double>(), counts);
	}

	double search(Map<String, Double> cache, int[] counts) {
		if (Arrays.stream(counts).allMatch(count -> count == 0)) {
			return 0;
		}

		String key = generateKey(counts);

		if (cache.containsKey(key)) {
			return cache.get(key);
		}

		double minCost = Double.MAX_VALUE;
		for (int code = 1; code < (1 << counts.length); code++) {
			boolean[] used = decode(counts.length, code);

			if (IntStream.range(0, used.length).anyMatch(i -> counts[i] == 0 && used[i])) {
				continue;
			}

			int bookNum = (int) IntStream.range(0, used.length).filter(i -> used[i]).count();

			minCost = Math.min(minCost, bookNum * PRICE * RATIOS[bookNum] + search(cache,
					IntStream.range(0, counts.length).map(i -> counts[i] - (used[i] ? 1 : 0)).toArray()));
		}

		cache.put(key, minCost);
		return minCost;
	}

	String generateKey(int[] counts) {
		return String.join(",", Arrays.stream(counts).mapToObj(Integer::toString).collect(Collectors.toList()));
	}

	boolean[] decode(int size, int code) {
		boolean[] result = new boolean[size];
		for (int i = 0; i < result.length; i++) {
			result[i] = (code & 1) != 0;
			code >>= 1;
		}
		return result;
	}
}
