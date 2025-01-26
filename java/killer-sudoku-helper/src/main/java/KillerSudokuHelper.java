import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class KillerSudokuHelper {
  List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize, List<Integer> exclude) {
    List<List<Integer>> combinations = new ArrayList<>();
    search(combinations, cageSize, Set.copyOf(exclude), new ArrayList<>(), cageSum);

    return combinations;
  }

  void search(
      List<List<Integer>> combinations,
      int cageSize,
      Set<Integer> excludes,
      List<Integer> combination,
      int rest) {
    if (combination.size() == cageSize) {
      if (rest == 0) {
        combinations.add(List.copyOf(combination));
      }

      return;
    }

    for (int d = combination.isEmpty() ? 1 : (combination.getLast() + 1); d <= 9; ++d) {
      if (!excludes.contains(d)) {
        combination.add(d);
        search(combinations, cageSize, excludes, combination, rest - d);
        combination.removeLast();
      }
    }
  }

  List<List<Integer>> combinationsInCage(Integer cageSum, Integer cageSize) {
    return combinationsInCage(cageSum, cageSize, List.of());
  }
}
