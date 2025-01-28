import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class School {
  Map<String, Integer> nameToGrade = new HashMap<>();

  boolean add(String name, int grade) {
    if (nameToGrade.containsKey(name)) {
      return false;
    }

    nameToGrade.put(name, grade);

    return true;
  }

  List<String> grade(int grade) {
    return nameToGrade.keySet().stream()
        .filter(name -> nameToGrade.get(name) == grade)
        .sorted()
        .toList();
  }

  List<String> roster() {
    return nameToGrade.keySet().stream()
        .sorted(Comparator.<String, Integer>comparing(nameToGrade::get).thenComparing(name -> name))
        .toList();
  }
}
