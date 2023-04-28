import java.util.stream.IntStream;

public class RectangleCounter {
  int countRectangles(String[] grid) {
    int row = grid.length;
    if (row == 0) {
      return 0;
    }

    int col = grid[0].length();

    int result = 0;
    for (int r1 = 0; r1 < row; ++r1) {
      for (int c1 = 0; c1 < col; ++c1) {
        for (int r2 = r1 + 1; r2 < row; ++r2) {
          for (int c2 = c1 + 1; c2 < col; ++c2) {
            if (isRectangle(grid, r1, c1, r2, c2)) {
              ++result;
            }
          }
        }
      }
    }

    return result;
  }

  boolean isRectangle(String[] grid, int r1, int c1, int r2, int c2) {
    return grid[r1].charAt(c1) == '+'
        && grid[r1].charAt(c2) == '+'
        && grid[r2].charAt(c1) == '+'
        && grid[r2].charAt(c2) == '+'
        && IntStream.range(c1 + 1, c2)
            .allMatch(
                c ->
                    "-+".indexOf(grid[r1].charAt(c)) != -1
                        && "-+".indexOf(grid[r2].charAt(c)) != -1)
        && IntStream.range(r1 + 1, r2)
            .allMatch(
                r ->
                    "|+".indexOf(grid[r].charAt(c1)) != -1
                        && "|+".indexOf(grid[r].charAt(c2)) != -1);
  }
}
