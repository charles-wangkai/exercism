import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Series {
	String s;

	Series(String s) {
		this.s = s;
	}

	List<String> slices(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Slice size is too small.");
		}
		if (length > s.length()) {
			throw new IllegalArgumentException("Slice size is too big.");
		}

		return IntStream.rangeClosed(0, s.length() - length).mapToObj(i -> s.substring(i, i + length))
				.collect(Collectors.toList());
	}
}