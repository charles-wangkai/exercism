public class QueenAttackCalculator {
	BoardCoordinate pos1;
	BoardCoordinate pos2;

	QueenAttackCalculator(BoardCoordinate pos1, BoardCoordinate pos2) {
		if (pos1 == null || pos2 == null) {
			throw new IllegalArgumentException("You must supply valid board coordinates for both Queens.");
		}
		if (pos1.equals(pos2)) {
			throw new IllegalArgumentException("Queens may not occupy the same board coordinate.");
		}

		this.pos1 = pos1;
		this.pos2 = pos2;
	}

	boolean canQueensAttackOneAnother() {
		return pos1.row == pos2.row || pos1.col == pos2.col || pos1.row + pos1.col == pos2.row + pos2.col
				|| pos1.row - pos1.col == pos2.row - pos2.col;
	}
}
