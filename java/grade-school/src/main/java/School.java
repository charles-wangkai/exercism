import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class School {
	Map<String, Integer> nameToGrade = new HashMap<String, Integer>();

	void add(String name, int grade) {
		nameToGrade.put(name, grade);
	}

	List<String> grade(int grade) {
		return nameToGrade.entrySet().stream().filter(entry -> entry.getValue() == grade).map(entry -> entry.getKey())
				.sorted().collect(Collectors.toList());
	}

	List<String> roster() {
		return nameToGrade.keySet().stream()
				.sorted((name1, name2) -> (nameToGrade.get(name1) != nameToGrade.get(name2))
						? Integer.compare(nameToGrade.get(name1), nameToGrade.get(name2))
						: name1.compareTo(name2))
				.collect(Collectors.toList());
	}
}