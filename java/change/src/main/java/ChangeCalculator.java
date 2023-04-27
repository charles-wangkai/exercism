import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
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

    Map<Integer, List<Integer>> valueToCoins = new HashMap<>();
    valueToCoins.put(0, List.of());
    Queue<Integer> queue = new ArrayDeque<>();
    queue.offer(0);
    while (!queue.isEmpty()) {
      int head = queue.poll();
      if (head == change) {
        return valueToCoins.get(head);
      }

      for (int coin : coins) {
        int nextValue = head + coin;
        if (nextValue <= change && !valueToCoins.containsKey(nextValue)) {
          List<Integer> nextCoins = new ArrayList<>(valueToCoins.get(head));
          nextCoins.add(coin);

          valueToCoins.put(nextValue, nextCoins);
          queue.offer(nextValue);
        }
      }
    }

    throw new IllegalArgumentException(
        String.format("The total %d cannot be represented in the given currency.", change));
  }
}
