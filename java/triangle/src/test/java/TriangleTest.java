import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

public class TriangleTest {

  @Test
  public void equilateralTrianglesHaveEqualSides() throws TriangleException {
    Triangle triangle = new Triangle(2, 2, 2);

    assertThat(triangle.isEquilateral()).isTrue();
  }

  @Test
  public void trianglesWithOneUnequalSideAreNotEquilateral() throws TriangleException {
    Triangle triangle = new Triangle(2, 3, 2);

    assertThat(triangle.isEquilateral()).isFalse();
  }

  @Test
  public void trianglesWithNoEqualSidesAreNotEquilateral() throws TriangleException {
    Triangle triangle = new Triangle(5, 4, 6);

    assertThat(triangle.isEquilateral()).isFalse();
  }

  @Test
  public void trianglesWithNoSizeAreIllegal() {
    assertThatExceptionOfType(TriangleException.class).isThrownBy(() -> new Triangle(0, 0, 0));
  }

  @Test
  public void verySmallTrianglesCanBeEquilateral() throws TriangleException {
    Triangle triangle = new Triangle(0.5, 0.5, 0.5);

    assertThat(triangle.isEquilateral()).isTrue();
  }

  @Test
  public void isoscelesTrianglesHaveLastTwoSidesEqual() throws TriangleException {
    Triangle triangle = new Triangle(3, 4, 4);

    assertThat(triangle.isIsosceles()).isTrue();
  }

  @Test
  public void isoscelesTrianglesHaveTwoFirstSidesEqual() throws TriangleException {
    Triangle triangle = new Triangle(4, 4, 3);

    assertThat(triangle.isIsosceles()).isTrue();
  }

  @Test
  public void isoscelesTrianglesHaveFirstAndLastSidesEqual() throws TriangleException {
    Triangle triangle = new Triangle(4, 3, 4);

    assertThat(triangle.isIsosceles()).isTrue();
  }

  @Test
  public void equilateralTrianglesAreAlsoIsosceles() throws TriangleException {
    Triangle triangle = new Triangle(4, 4, 4);

    assertThat(triangle.isIsosceles()).isTrue();
  }

  @Test
  public void noSidesAreEqualCantBeIsoceles() throws TriangleException {
    Triangle triangle = new Triangle(2, 3, 4);

    assertThat(triangle.isIsosceles()).isFalse();
  }

  @Test
  public void firstTriangleInequalityViolation() {
    assertThatExceptionOfType(TriangleException.class).isThrownBy(() -> new Triangle(1, 1, 3));
  }

  @Test
  public void secondTriangleInequalityViolation() {
    assertThatExceptionOfType(TriangleException.class).isThrownBy(() -> new Triangle(1, 3, 1));
  }

  @Test
  public void thirdTriangleInequalityViolation() {
    assertThatExceptionOfType(TriangleException.class).isThrownBy(() -> new Triangle(3, 1, 1));
  }

  @Test
  public void verySmallTrianglesCanBeIsosceles() throws TriangleException {
    Triangle triangle = new Triangle(0.5, 0.4, 0.5);

    assertThat(triangle.isIsosceles()).isTrue();
  }

  @Test
  public void scaleneTrianglesHaveNoEqualSides() throws TriangleException {
    Triangle triangle = new Triangle(5, 4, 6);

    assertThat(triangle.isScalene()).isTrue();
  }

  @Test
  public void allSidesEqualAreNotScalene() throws TriangleException {
    Triangle triangle = new Triangle(4, 4, 4);

    assertThat(triangle.isScalene()).isFalse();
  }

  @Test
  public void twoSidesEqualAreNotScalene() throws TriangleException {
    Triangle triangle = new Triangle(4, 4, 3);

    assertThat(triangle.isScalene()).isFalse();
  }

  @Test
  public void firstAndThirdSidesAreEqualAreNotScalene() throws TriangleException {
    Triangle triangle = new Triangle(3, 4, 3);

    assertThat(triangle.isScalene()).isFalse();
  }

  @Test
  public void secondAndThirdSidesAreEqualAreNotScalene() throws TriangleException {
    Triangle triangle = new Triangle(4, 3, 3);

    assertThat(triangle.isScalene()).isFalse();
  }

  @Test
  public void mayNotViolateTriangleInequality() {
    assertThatExceptionOfType(TriangleException.class).isThrownBy(() -> new Triangle(7, 3, 2));
  }

  @Test
  public void verySmallTrianglesCanBeScalene() throws TriangleException {
    Triangle triangle = new Triangle(0.5, 0.4, 0.6);

    assertThat(triangle.isScalene()).isTrue();
  }
}
