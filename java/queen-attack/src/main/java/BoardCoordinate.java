public class BoardCoordinate {
	int row;
	int col;

	BoardCoordinate(int row, int col) {
		if (row > 7) {
			throw new IllegalArgumentException("Coordinate must have rank <= 7.");
		}
		if (col > 7) {
			throw new IllegalArgumentException("Coordinate must have file <= 7.");
		}
		if (row < 0) {
			throw new IllegalArgumentException("Coordinate must have positive rank.");
		}
		if (col < 0) {
			throw new IllegalArgumentException("Coordinate must have positive file.");
		}

		this.row = row;
		this.col = col;
	}

	@Override
	public boolean equals(Object obj) {
		BoardCoordinate other = (BoardCoordinate) obj;
		return row == other.row && col == other.col;
	}
}
