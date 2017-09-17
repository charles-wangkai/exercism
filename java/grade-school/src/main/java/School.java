import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class School {
	Map<String, Integer> name2grade = new HashMap<String, Integer>();

	int numberOfStudents() {
		return name2grade.size();
	}

	void add(String name, int grade) {
		name2grade.put(name, grade);
	}

	List<String> grade(int grade) {
		return name2grade.entrySet().stream().filter(entry -> entry.getValue() == grade).map(entry -> entry.getKey())
				.sorted().collect(Collectors.toList());
	}

	Map<Integer, List<String>> studentsByGradeAlphabetical() {
		return name2grade.values().stream().distinct().collect(Collectors.toMap(Function.identity(), this::grade));
	}
}
