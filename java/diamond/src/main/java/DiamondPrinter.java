import java.util.ArrayList;
import java.util.List;

public class DiamondPrinter {
  List<String> printToList(char letter) {
    List<String> lines = new ArrayList<>();

    int width = (letter - 'A') * 2 + 1;
    for (int i = 0; i < letter - 'A' + 1; ++i) {
      StringBuilder line = new StringBuilder(" ".repeat(width));
      line.setCharAt(width / 2 + i, (char) (i + 'A'));
      line.setCharAt(width / 2 - i, (char) (i + 'A'));

      lines.add(line.toString());
    }

    List<String> lowerHalf = new ArrayList<>();
    for (int i = lines.size() - 2; i >= 0; --i) {
      lowerHalf.add(lines.get(i));
    }

    lines.addAll(lowerHalf);

    return lines;
  }
}
