import java.util.Arrays;
import java.util.List;

public class MinesweeperBoard {
  List<String> inputBoard;

  MinesweeperBoard(List<String> inputBoard) {
    this.inputBoard = inputBoard;
  }

  List<String> withNumbers() {
    if (inputBoard.isEmpty()) {
      return List.of();
    }

    int row = inputBoard.size();
    int col = inputBoard.get(0).length();

    char[][] board = new char[row][col];
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        board[r][c] = transform(inputBoard, r, c);
      }
    }

    return Arrays.stream(board).map(String::new).toList();
  }

  char transform(List<String> inputBoard, int r, int c) {
    int row = inputBoard.size();
    int col = inputBoard.get(0).length();

    if (inputBoard.get(r).charAt(c) == '*') {
      return '*';
    }

    int count = 0;
    for (int dr = -1; dr <= 1; ++dr) {
      for (int dc = -1; dc <= 1; ++dc) {
        int adjR = r + dr;
        int adjC = c + dc;
        if (adjR >= 0
            && adjR < row
            && adjC >= 0
            && adjC < col
            && inputBoard.get(adjR).charAt(adjC) == '*') {
          ++count;
        }
      }
    }

    return (count == 0) ? ' ' : (char) (count + '0');
  }
}
