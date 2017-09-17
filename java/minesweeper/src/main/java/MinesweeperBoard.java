import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MinesweeperBoard {
	static final int[] R_OFFSETS = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] C_OFFSETS = { 0, 1, 1, 1, 0, -1, -1, -1 };

	List<String> inputBoard;

	MinesweeperBoard(List<String> inputBoard) {
		this.inputBoard = inputBoard;
	}

	List<String> withNumbers() {
		if (inputBoard.isEmpty()) {
			return Collections.emptyList();
		}

		int row = inputBoard.size();
		int col = inputBoard.get(0).length();

		char[][] board = new char[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (inputBoard.get(r).charAt(c) == '*') {
					board[r][c] = '*';
				} else {
					int count = 0;
					for (int i = 0; i < R_OFFSETS.length; i++) {
						int adjR = r + R_OFFSETS[i];
						int adjC = c + C_OFFSETS[i];
						if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col
								&& inputBoard.get(adjR).charAt(adjC) == '*') {
							count++;
						}
					}

					if (count == 0) {
						board[r][c] = ' ';
					} else {
						board[r][c] = (char) (count + '0');
					}
				}
			}
		}
		return Arrays.stream(board).map(String::new).collect(Collectors.toList());
	}
}
