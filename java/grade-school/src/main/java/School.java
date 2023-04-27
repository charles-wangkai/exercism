import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {
  Map<String, Integer> nameToGrade = new HashMap<>();

  void add(String name, int grade) {
    nameToGrade.put(name, grade);
  }

  List<String> grade(int grade) {
    return nameToGrade.keySet().stream()
        .filter(name -> nameToGrade.get(name) == grade)
        .sorted()
        .toList();
  }

  List<String> roster() {
    return nameToGrade.keySet().stream()
        .sorted(
            Comparator.comparing((String name) -> nameToGrade.get(name))
                .thenComparing(name -> name))
        .toList();
  }
}
