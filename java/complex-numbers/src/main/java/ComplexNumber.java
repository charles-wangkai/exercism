public class ComplexNumber {
  double real;
  double imaginary;

  ComplexNumber(double real, double imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

  public double getReal() {
    return real;
  }

  public double getImaginary() {
    return imaginary;
  }

  ComplexNumber add(ComplexNumber c) {
    return new ComplexNumber(real + c.real, imaginary + c.imaginary);
  }

  ComplexNumber subtract(ComplexNumber c) {
    return add(new ComplexNumber(-c.real, -c.imaginary));
  }

  ComplexNumber multiply(ComplexNumber c) {
    return new ComplexNumber(
        real * c.real - imaginary * c.imaginary, real * c.imaginary + imaginary * c.real);
  }

  ComplexNumber divide(ComplexNumber c) {
    return multiply(c.conjugate()).multiply(new ComplexNumber(1 / c.abs() / c.abs(), 0));
  }

  double abs() {
    return Math.sqrt(real * real + imaginary * imaginary);
  }

  ComplexNumber conjugate() {
    return new ComplexNumber(real, -imaginary);
  }

  ComplexNumber exponentialOf() {
    return new ComplexNumber(Math.exp(real), 0)
        .multiply(new ComplexNumber(Math.cos(imaginary), Math.sin(imaginary)));
  }
}
