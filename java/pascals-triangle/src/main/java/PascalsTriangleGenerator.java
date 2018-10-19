public class PascalsTriangleGenerator {
	int[][] generateTriangle(int rowNum) {
		if (rowNum < 0) {
			throw new IllegalArgumentException();
		}

		int[][] triangle = new int[rowNum][];
		for (int i = 0; i < triangle.length; i++) {
			triangle[i] = new int[i + 1];
			triangle[i][0] = 1;
			triangle[i][i] = 1;
			for (int j = 1; j < i; j++) {
				triangle[i][j] = triangle[i - 1][j - 1] + triangle[i - 1][j];
			}
		}
		return triangle;
	}
}