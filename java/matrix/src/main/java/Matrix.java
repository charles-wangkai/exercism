public class Matrix {
	private final int[][] matrix;

	public Matrix(String s) {
		String[] lines = s.split("\n");
		matrix = new int[lines.length][];
		for (int r = 0; r < matrix.length; r++) {
			String[] fields = lines[r].split(" ");
			matrix[r] = new int[fields.length];
			for (int c = 0; c < matrix[r].length; c++) {
				matrix[r][c] = Integer.parseInt(fields[c]);
			}
		}
	}

	public int[] getRow(int index) {
		return matrix[index];
	}

	public int[] getColumn(int index) {
		int[] column = new int[matrix.length];
		for (int r = 0; r < column.length; r++) {
			column[r] = matrix[r][index];
		}
		return column;
	}
}