class StateOfTicTacToe {
  public GameState determineState(String[] board) {
    int xCount = count(board, 'X');
    int oCount = count(board, 'O');
    if (xCount > oCount + 1) {
      throw new IllegalArgumentException("Wrong turn order: X went twice");
    }
    if (xCount < oCount) {
      throw new IllegalArgumentException("Wrong turn order: O started");
    }

    boolean xWin = isWin(board, 'X');
    boolean oWin = isWin(board, 'O');
    if ((xWin && (oWin || xCount != oCount + 1)) || (oWin && oCount != xCount)) {
      throw new IllegalArgumentException(
          "Impossible board: game should have ended after the game was won");
    }

    if (xWin || oWin) {
      return GameState.WIN;
    }

    return (xCount + oCount == 9) ? GameState.DRAW : GameState.ONGOING;
  }

  boolean isWin(String[] board, char piece) {
    return (board[0].charAt(0) == piece
            && board[0].charAt(1) == piece
            && board[0].charAt(2) == piece)
        || (board[1].charAt(0) == piece
            && board[1].charAt(1) == piece
            && board[1].charAt(2) == piece)
        || (board[2].charAt(0) == piece
            && board[2].charAt(1) == piece
            && board[2].charAt(2) == piece)
        || (board[0].charAt(0) == piece
            && board[1].charAt(0) == piece
            && board[2].charAt(0) == piece)
        || (board[0].charAt(1) == piece
            && board[1].charAt(1) == piece
            && board[2].charAt(1) == piece)
        || (board[0].charAt(2) == piece
            && board[1].charAt(2) == piece
            && board[2].charAt(2) == piece)
        || (board[0].charAt(0) == piece
            && board[1].charAt(1) == piece
            && board[2].charAt(2) == piece)
        || (board[0].charAt(2) == piece
            && board[1].charAt(1) == piece
            && board[2].charAt(0) == piece);
  }

  int count(String[] board, char piece) {
    int result = 0;
    for (String line : board) {
      for (char cell : line.toCharArray()) {
        if (cell == piece) {
          ++result;
        }
      }
    }

    return result;
  }
}
