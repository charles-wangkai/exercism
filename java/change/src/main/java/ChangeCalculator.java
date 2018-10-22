import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ChangeCalculator {
	List<Integer> coins;

	ChangeCalculator(List<Integer> coins) {
		this.coins = coins;
	}

	List<Integer> computeMostEfficientChange(int change) {
		if (change < 0) {
			throw new IllegalArgumentException("Negative totals are not allowed.");
		}

		Map<Integer, List<Integer>> value2coins = new HashMap<Integer, List<Integer>>();
		value2coins.put(0, Collections.emptyList());
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		while (!queue.isEmpty()) {
			int value = queue.poll();

			if (value == change) {
				return value2coins.get(value);
			}

			for (int coin : coins) {
				int nextValue = value + coin;
				if (nextValue <= change && !value2coins.containsKey(nextValue)) {
					List<Integer> nextCoins = new ArrayList<Integer>(value2coins.get(value));
					nextCoins.add(coin);

					value2coins.put(nextValue, nextCoins);
					queue.offer(nextValue);
				}
			}
		}

		throw new IllegalArgumentException(
				String.format("The total %d cannot be represented in the given currency.", change));
	}
}