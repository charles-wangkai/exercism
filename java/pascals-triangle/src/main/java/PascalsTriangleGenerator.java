public class PascalsTriangleGenerator {
  int[][] generateTriangle(int rowNum) {
    if (rowNum < 0) {
      throw new IllegalArgumentException();
    }

    int[][] result = new int[rowNum][];
    for (int i = 0; i < result.length; ++i) {
      result[i] = new int[i + 1];
      result[i][0] = 1;
      result[i][i] = 1;
      for (int j = 1; j < i; ++j) {
        result[i][j] = result[i - 1][j - 1] + result[i - 1][j];
      }
    }

    return result;
  }
}
