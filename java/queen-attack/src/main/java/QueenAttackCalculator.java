public class QueenAttackCalculator {
	Queen pos1;
	Queen pos2;

	QueenAttackCalculator(Queen pos1, Queen pos2) {
		if (pos1 == null || pos2 == null) {
			throw new IllegalArgumentException("You must supply valid positions for both Queens.");
		}
		if (pos1.equals(pos2)) {
			throw new IllegalArgumentException("Queens cannot occupy the same position.");
		}

		this.pos1 = pos1;
		this.pos2 = pos2;
	}

	boolean canQueensAttackOneAnother() {
		return pos1.row == pos2.row || pos1.col == pos2.col || pos1.row + pos1.col == pos2.row + pos2.col
				|| pos1.row - pos1.col == pos2.row - pos2.col;
	}
}