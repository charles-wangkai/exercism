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
    Set<MatrixCoordinate> saddlePoints = new HashSet<>();
    for (int r = 0; r < getNumberOfRows(); ++r) {
      for (int c = 0; c < getNumberOfColumns(); ++c) {
        if (isSaddlePoint(r, c)) {
          saddlePoints.add(new MatrixCoordinate(r + 1, c + 1));
        }
      }
    }

    return saddlePoints;
  }

  private int getNumberOfRows() {
    return elements.size();
  }

  private int getNumberOfColumns() {
    return elements.get(0).size();
  }

  private boolean isSaddlePoint(int r, int c) {
    return isMaxInRow(r, c) && isMinInColumn(r, c);
  }

  private boolean isMaxInRow(int r, int c) {
    return IntStream.range(0, getNumberOfColumns())
        .allMatch(c1 -> elements.get(r).get(c1) <= elements.get(r).get(c));
  }

  private boolean isMinInColumn(int r, int c) {
    return IntStream.range(0, getNumberOfRows())
        .allMatch(r1 -> elements.get(r1).get(c) >= elements.get(r).get(c));
  }
}
