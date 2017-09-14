import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Matrix {
	List<List<Integer>> elements;

	Matrix(List<List<Integer>> elements) {
		this.elements = elements;
	}

	Set<MatrixCoordinate> getSaddlePoints() {
		Set<MatrixCoordinate> saddlePoints = new HashSet<MatrixCoordinate>();
		for (int r = 0; r < getRow(); r++) {
			for (int c = 0; c < getCol(); c++) {
				if (isSaddlePoint(r, c)) {
					saddlePoints.add(new MatrixCoordinate(r, c));
				}
			}
		}
		return saddlePoints;
	}

	int getRow() {
		return elements.size();
	}

	int getCol() {
		return getRow() == 0 ? 0 : elements.get(0).size();
	}

	boolean isSaddlePoint(int r, int c) {
		for (int y = 0; y < getCol(); y++) {
			if (elements.get(r).get(y) > elements.get(r).get(c)) {
				return false;
			}
		}

		for (int x = 0; x < getRow(); x++) {
			if (elements.get(x).get(c) < elements.get(r).get(c)) {
				return false;
			}
		}

		return true;
	}
}
