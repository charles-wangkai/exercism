import java.util.Arrays;
import java.util.stream.Collectors;

public class Transpose {
	String transpose(String s) {
		String[] lines = s.split("\n");

		int row = lines.length;
		int col = Arrays.stream(lines).mapToInt(String::length).max().getAsInt();

		Character[][] transposed = new Character[col][row];

		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length(); j++) {
				transposed[j][i] = lines[i].charAt(j);
			}
		}

		return String.join("\n", Arrays.stream(transposed).map(this::convert).collect(Collectors.toList()));
	}

	String convert(Character[] r) {
		int lastIndex = r.length - 1;
		while (lastIndex >= 0 && r[lastIndex] == null) {
			lastIndex--;
		}
		return Arrays.stream(r, 0, lastIndex + 1).map(ch -> ch == null ? ' ' : ch)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}
}
