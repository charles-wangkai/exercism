import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Series {
	String s;

	Series(String s) {
		this.s = s;
	}

	List<Integer> getDigits() {
		return s.chars().map(ch -> ch - '0').collect(ArrayList<Integer>::new, List::add, List::addAll);
	}

	List<List<Integer>> slices(int length) {
		List<Integer> digits = getDigits();

		if (length > digits.size()) {
			throw new IllegalArgumentException();
		}

		return IntStream.rangeClosed(0, digits.size() - length).mapToObj(i -> digits.subList(i, i + length))
				.collect(Collectors.toList());
	}
}
