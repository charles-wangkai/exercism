import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Matrix {
	private List<List<Integer>> elements;

	public Matrix(List<List<Integer>> elements) {
		this.elements = elements;
	}

	public Set<MatrixCoordinate> getSaddlePoints() {
		Set<MatrixCoordinate> saddlePoints = new HashSet<MatrixCoordinate>();
		for (int r = 0; r < getRowNum(); r++) {
			for (int c = 0; c < getColumnNum(); c++) {
				if (isSaddlePoint(r, c)) {
					saddlePoints.add(new MatrixCoordinate(r, c));
				}
			}
		}
		return saddlePoints;
	}

	private int getRowNum() {
		return elements.size();
	}

	private int getColumnNum() {
		return elements.get(0).size();
	}

	private boolean isSaddlePoint(int r, int c) {
		return isMaximumInRow(r, c) && isMinimumInColumn(r, c);
	}

	private boolean isMaximumInRow(int r, int c) {
		return IntStream.range(0, getColumnNum()).allMatch(y -> elements.get(r).get(y) <= elements.get(r).get(c));
	}

	private boolean isMinimumInColumn(int r, int c) {
		return IntStream.range(0, getRowNum()).allMatch(x -> elements.get(x).get(c) >= elements.get(r).get(c));
	}
}