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
				board[r][c] = transform(inputBoard, r, c);
			}
		}

		return Arrays.stream(board).map(String::new).collect(Collectors.toList());
	}

	char transform(List<String> inputBoard, int r, int c) {
		if (inputBoard.get(r).charAt(c) == '*') {
			return '*';
		}

		int count = 0;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int adjacentR = r + R_OFFSETS[i];
			int adjacentC = c + C_OFFSETS[i];

			if (isInbound(inputBoard, adjacentR, adjacentC) && inputBoard.get(adjacentR).charAt(adjacentC) == '*') {
				count++;
			}
		}

		if (count == 0) {
			return ' ';
		} else {
			return (char) (count + '0');
		}
	}

	boolean isInbound(List<String> inputBoard, int r, int c) {
		int row = inputBoard.size();
		int col = inputBoard.get(0).length();

		return r >= 0 && r < row && c >= 0 && c < col;
	}
}