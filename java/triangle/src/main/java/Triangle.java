import java.util.stream.DoubleStream;

class Triangle {
  private static double EPSILON = 1e-9;

  private double[] sides;

  Triangle(double side1, double side2, double side3) throws TriangleException {
    sides = DoubleStream.of(side1, side2, side3).sorted().toArray();

    if (compareDouble(sides[0], 0) <= 0 || compareDouble(sides[0] + sides[1], sides[2]) <= 0) {
      throw new TriangleException();
    }
  }

  boolean isEquilateral() {
    return compareDouble(sides[0], sides[2]) == 0;
  }

  boolean isIsosceles() {
    return compareDouble(sides[0], sides[1]) == 0 || compareDouble(sides[1], sides[2]) == 0;
  }

  boolean isScalene() {
    return !isEquilateral() && !isIsosceles();
  }

  int compareDouble(double value1, double value2) {
    if (value1 < value2 - EPSILON) {
      return -1;
    }
    if (value1 > value2 + EPSILON) {
      return 1;
    }

    return 0;
  }
}
