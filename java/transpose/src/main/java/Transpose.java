import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Transpose {
  String transpose(String s) {
    String[] lines = s.split("\n");

    int row = lines.length;
    int col = Arrays.stream(lines).mapToInt(String::length).max().getAsInt();

    char[][] transposed = new char[col][row];
    for (int i = 0; i < lines.length; ++i) {
      for (int j = 0; j < lines[i].length(); ++j) {
        transposed[j][i] = lines[i].charAt(j);
      }
    }

    return Arrays.stream(transposed)
        .map(
            line -> {
              int lastIndex = line.length - 1;
              while (lastIndex != -1 && line[lastIndex] == 0) {
                --lastIndex;
              }

              return IntStream.range(0, lastIndex + 1)
                  .mapToObj(i -> (line[i] == 0) ? ' ' : line[i])
                  .map(String::valueOf)
                  .collect(Collectors.joining());
            })
        .collect(Collectors.joining("\n"));
  }
}
