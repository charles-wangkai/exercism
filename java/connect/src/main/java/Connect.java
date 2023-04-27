import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Connect {
  static final int[] R_OFFSETS = {-1, -1, 0, 0, 1, 1};
  static final int[] C_OFFSETS = {0, 1, -1, 1, -1, 0};

  String[] board;

  public Connect(String[] board) {
    this.board = Arrays.stream(board).map(line -> line.replace(" ", "")).toArray(String[]::new);
  }

  public Winner computeWinner() {
    int row = board.length;
    int col = board[0].length();

    for (int c = 0; c < col; ++c) {
      if (board[0].charAt(c) == 'O'
          && findConnectedCells(0, c).stream().anyMatch(p -> p.r() == row - 1)) {
        return Winner.PLAYER_O;
      }
    }

    for (int r = 0; r < row; ++r) {
      if (board[r].charAt(0) == 'X'
          && findConnectedCells(r, 0).stream().anyMatch(p -> p.c() == col - 1)) {
        return Winner.PLAYER_X;
      }
    }

    return Winner.NONE;
  }

  Set<Point> findConnectedCells(int r, int c) {
    Set<Point> connectedCells = new HashSet<>();
    search(connectedCells, r, c);

    return connectedCells;
  }

  void search(Set<Point> connectedCells, int r, int c) {
    int row = board.length;
    int col = board[0].length();

    connectedCells.add(new Point(r, c));

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int adjR = r + R_OFFSETS[i];
      int adjC = c + C_OFFSETS[i];
      if (adjR >= 0
          && adjR < row
          && adjC >= 0
          && adjC < col
          && board[adjR].charAt(adjC) == board[r].charAt(c)
          && !connectedCells.contains(new Point(adjR, adjC))) {
        search(connectedCells, adjR, adjC);
      }
    }
  }
}

record Point(int r, int c) {}
