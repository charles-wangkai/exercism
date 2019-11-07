import java.util.List;
import java.util.stream.IntStream;

public class Knapsack {
	public int maximumValue(int capacity, List<Item> items) {
		return IntStream.range(0, 1 << items.size()).map(code -> {
			boolean[] used = decode(items.size(), code);

			int weightSum = IntStream.range(0, items.size()).filter(i -> used[i]).map(i -> items.get(i).weight).sum();
			int valueSum = IntStream.range(0, items.size()).filter(i -> used[i]).map(i -> items.get(i).value).sum();

			return (weightSum <= capacity) ? valueSum : -1;
		}).max().getAsInt();
	}

	boolean[] decode(int size, int code) {
		boolean[] used = new boolean[size];
		for (int i = 0; i < used.length; i++) {
			used[i] = (code & 1) != 0;

			code >>= 1;
		}

		return used;
	}
}