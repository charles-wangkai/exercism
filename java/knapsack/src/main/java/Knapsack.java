import java.util.List;
import java.util.stream.IntStream;

public class Knapsack {
  public int maximumValue(int capacity, List<Item> items) {
    return IntStream.range(0, 1 << items.size())
        .map(
            mask -> {
              int weightSum =
                  IntStream.range(0, items.size())
                      .filter(i -> ((mask >> i) & 1) == 1)
                      .map(i -> items.get(i).weight)
                      .sum();
              int valueSum =
                  IntStream.range(0, items.size())
                      .filter(i -> ((mask >> i) & 1) == 1)
                      .map(i -> items.get(i).value)
                      .sum();

              return (weightSum <= capacity) ? valueSum : -1;
            })
        .max()
        .getAsInt();
  }
}
