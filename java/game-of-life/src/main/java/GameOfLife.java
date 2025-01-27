class GameOfLife {
  public int[][] tick(int[][] matrix) {
    int row = matrix.length;
    if (row == 0) {
      return matrix;
    }

    int col = matrix[0].length;

    int[][] result = new int[row][col];
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        int neighborLiveCount = computeNeighborLiveCount(matrix, r, c);
        if (matrix[r][c] == 1) {
          result[r][c] = (neighborLiveCount == 2 || neighborLiveCount == 3) ? 1 : 0;
        } else {
          result[r][c] = (neighborLiveCount == 3) ? 1 : 0;
        }
      }
    }

    return result;
  }

  int computeNeighborLiveCount(int[][] matrix, int r, int c) {
    int row = matrix.length;
    int col = matrix[0].length;

    int result = 0;
    for (int dr = -1; dr <= 1; ++dr) {
      for (int dc = -1; dc <= 1; ++dc) {
        int adjR = r + dr;
        int adjC = c + dc;
        if (adjR >= 0
            && adjR < row
            && adjC >= 0
            && adjC < col
            && (dr != 0 || dc != 0)
            && matrix[adjR][adjC] == 1) {
          ++result;
        }
      }
    }

    return result;
  }
}
