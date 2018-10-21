import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RailFenceCipher {
	int rails;

	RailFenceCipher(int rails) {
		this.rails = rails;
	}

	private List<Integer>[] getFencePattern(int messageSize) {
		@SuppressWarnings("unchecked")
		List<Integer>[] pattern = new List[rails];
		for (int i = 0; i < pattern.length; i++) {
			pattern[i] = new ArrayList<Integer>();
		}
		int row = 0;
		int rowOffset = 1;
		for (int col = 0; col < messageSize; col++) {
			pattern[row].add(col);

			row += rowOffset;
			if (!(row >= 0 && row < pattern.length)) {
				rowOffset *= -1;
				row += rowOffset * 2;
			}
		}
		return pattern;
	}

	String getEncryptedData(String message) {
		List<Integer>[] pattern = getFencePattern(message.length());

		return String.join("",
				Arrays.stream(pattern).map(line -> String.join("",
						line.stream().map(col -> String.valueOf(message.charAt(col))).collect(Collectors.toList())))
						.collect(Collectors.toList()));
	}

	String getDecryptedData(String encodedMessage) {
		List<Integer>[] pattern = getFencePattern(encodedMessage.length());

		StringBuilder result = new StringBuilder();
		result.setLength(encodedMessage.length());
		int index = 0;
		for (List<Integer> line : pattern) {
			for (int col : line) {
				result.setCharAt(col, encodedMessage.charAt(index));
				index++;
			}
		}
		return result.toString();
	}
}