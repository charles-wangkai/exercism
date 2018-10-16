import java.util.ArrayList;
import java.util.List;

public class DiamondPrinter {
	List<String> printToList(char letter) {
		int width = (letter - 'A') * 2 + 1;

		StringBuilder emptyLine = new StringBuilder();
		for (int i = 0; i < width; i++) {
			emptyLine.append(" ");
		}

		List<String> lines = new ArrayList<String>();
		for (int i = 0; i < letter - 'A' + 1; i++) {
			StringBuilder line = new StringBuilder(emptyLine.toString());
			line.setCharAt(width / 2 + i, (char) (i + 'A'));
			line.setCharAt(width / 2 - i, (char) (i + 'A'));

			lines.add(line.toString());
		}

		List<String> lowerHalf = new ArrayList<String>();
		for (int i = lines.size() - 2; i >= 0; i--) {
			lowerHalf.add(lines.get(i));
		}

		lines.addAll(lowerHalf);
		return lines;
	}
}