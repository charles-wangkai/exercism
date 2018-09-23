import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Matrix {
	List<List<Integer>> elements;

	Matrix(List<List<Integer>> elements) {
		this.elements = elements;
	}

	Set<MatrixCoordinate> getSaddlePoints() {
		Set<MatrixCoordinate> saddlePoints = new HashSet<MatrixCoordinate>();
		for (int r = 0; r < getRowNum(); r++) {
			for (int c = 0; c < getColNum(); c++) {
				if (isSaddlePoint(r, c)) {
					saddlePoints.add(new MatrixCoordinate(r, c));
				}
			}
		}
		return saddlePoints;
	}

	int getRowNum() {
		return elements.size();
	}

	int getColNum() {
		return elements.get(0).size();
	}

	boolean isSaddlePoint(int r, int c) {
		return isMaxInRow(r, c) && isMinInCol(r, c);
	}

	boolean isMaxInRow(int r, int c) {
		return IntStream.range(0, getColNum()).allMatch(y -> elements.get(r).get(y) <= elements.get(r).get(c));
	}

	boolean isMinInCol(int r, int c) {
		return IntStream.range(0, getRowNum()).allMatch(x -> elements.get(x).get(c) >= elements.get(r).get(c));
	}
}