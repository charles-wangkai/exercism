public class SpiralMatrixBuilder {
  static final int[] R_OFFSETS = {0, 1, 0, -1};
  static final int[] C_OFFSETS = {1, 0, -1, 0};

  int[][] buildMatrixOfSize(int size) {
    int[][] matrix = new int[size][size];
    int r = 0;
    int c = -1;
    int direction = 0;
    for (int i = 1; i <= size * size; ++i) {
      while (true) {
        int nextR = r + R_OFFSETS[direction];
        int nextC = c + C_OFFSETS[direction];
        if (nextR >= 0 && nextR < size && nextC >= 0 && nextC < size && matrix[nextR][nextC] == 0) {
          r = nextR;
          c = nextC;
          matrix[r][c] = i;

          break;
        }

        direction = (direction + 1) % R_OFFSETS.length;
      }
    }

    return matrix;
  }
}
