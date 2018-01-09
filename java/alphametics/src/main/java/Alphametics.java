import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Alphametics {
	String puzzle;

	Alphametics(String puzzle) {
		this.puzzle = puzzle;
	}

	Map<Character, Integer> solve() throws UnsolvablePuzzleException {
		List<Character> letters = puzzle.chars().filter(Character::isAlphabetic).mapToObj(ch -> (char) ch).distinct()
				.collect(Collectors.toList());
		int[] digits = IntStream.range(0, 10).toArray();

		Map<Character, Integer> letter2digit = search(letters, digits, 0);
		if (letter2digit == null) {
			throw new UnsolvablePuzzleException();
		}
		return letter2digit;
	}

	Map<Character, Integer> search(List<Character> letters, int[] digits, int index) {
		if (index == letters.size()) {
			Map<Character, Integer> letter2digit = IntStream.range(0, letters.size()).boxed()
					.collect(Collectors.toMap(i -> letters.get(i), i -> digits[i]));
			return check(letter2digit) ? letter2digit : null;
		}

		for (int i = index; i < digits.length; i++) {
			swap(digits, i, index);

			Map<Character, Integer> letter2digit = search(letters, digits, index + 1);
			if (letter2digit != null) {
				return letter2digit;
			}

			swap(digits, i, index);
		}

		return null;
	}

	void swap(int[] a, int index1, int index2) {
		int temp = a[index1];
		a[index1] = a[index2];
		a[index2] = temp;
	}

	boolean check(Map<Character, Integer> letter2digit) {
		String translated = puzzle;
		for (Character letter : letter2digit.keySet()) {
			translated = translated.replace(letter, (char) (letter2digit.get(letter) + '0'));
		}

		String[] items = translated.split("\\W+");

		if (Arrays.stream(items).anyMatch(item -> item.length() > 1 && item.startsWith("0"))) {
			return false;
		}

		return Arrays.stream(items, 0, items.length - 1).mapToLong(Long::parseLong).sum() == Long
				.parseLong(items[items.length - 1]);
	}
}
