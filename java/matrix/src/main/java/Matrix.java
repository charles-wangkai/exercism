import java.util.stream.IntStream;

public class Matrix {
  private final int[][] matrix;

  public Matrix(String s) {
    String[] lines = s.split("\n");
    matrix = new int[lines.length][];
    for (int r = 0; r < matrix.length; ++r) {
      String[] parts = lines[r].split(" ");
      matrix[r] = new int[parts.length];
      for (int c = 0; c < matrix[r].length; ++c) {
        matrix[r][c] = Integer.parseInt(parts[c]);
      }
    }
  }

  public int[] getRow(int index) {
    return matrix[index - 1];
  }

  public int[] getColumn(int index) {
    return IntStream.range(0, matrix.length).map(r -> matrix[r][index - 1]).toArray();
  }
}
