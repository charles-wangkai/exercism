import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BookStore {
  static final int PRICE = 8;
  static final double[] RATIOS = {-1, 1, 0.95, 0.9, 0.8, 0.75};

  double calculateBasketCost(List<Integer> books) {
    int[] counts = new int[5];
    for (int book : books) {
      ++counts[book - 1];
    }

    return search(new HashMap<String, Double>(), counts);
  }

  double search(Map<String, Double> cache, int[] counts) {
    if (Arrays.stream(counts).allMatch(count -> count == 0)) {
      return 0;
    }

    String key = generateKey(counts);
    if (!cache.containsKey(key)) {
      double minCost =
          IntStream.range(1, 1 << counts.length)
              .filter(
                  mask ->
                      IntStream.range(0, counts.length)
                          .allMatch(i -> counts[i] != 0 || ((mask >> i) & 1) == 0))
              .mapToDouble(
                  mask -> {
                    int bookNum = Integer.bitCount(mask);

                    return bookNum * PRICE * RATIOS[bookNum]
                        + search(
                            cache,
                            IntStream.range(0, counts.length)
                                .map(i -> counts[i] - ((mask >> i) & 1))
                                .toArray());
                  })
              .min()
              .getAsDouble();
      cache.put(key, minCost);
    }

    return cache.get(key);
  }

  String generateKey(int[] counts) {
    return Arrays.stream(counts).mapToObj(String::valueOf).collect(Collectors.joining(","));
  }
}
