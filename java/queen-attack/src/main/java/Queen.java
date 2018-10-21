public class Queen {
	int row;
	int col;

	Queen(int row, int col) {
		if (row > 7) {
			throw new IllegalArgumentException("Queen position must have row <= 7.");
		}
		if (col > 7) {
			throw new IllegalArgumentException("Queen position must have column <= 7.");
		}
		if (row < 0) {
			throw new IllegalArgumentException("Queen position must have positive row.");
		}
		if (col < 0) {
			throw new IllegalArgumentException("Queen position must have positive column.");
		}

		this.row = row;
		this.col = col;
	}

	@Override
	public boolean equals(Object obj) {
		Queen other = (Queen) obj;
		return row == other.row && col == other.col;
	}
}