public class ComplexNumber {
  double real;
  double imag;

  ComplexNumber(double real, double imag) {
    this.real = real;
    this.imag = imag;
  }

  public double getReal() {
    return real;
  }

  public double getImag() {
    return imag;
  }

  ComplexNumber add(ComplexNumber c) {
    return new ComplexNumber(real + c.real, imag + c.imag);
  }

  ComplexNumber minus(ComplexNumber c) {
    return add(new ComplexNumber(-c.real, -c.imag));
  }

  ComplexNumber times(ComplexNumber c) {
    return new ComplexNumber(real * c.real - imag * c.imag, real * c.imag + imag * c.real);
  }

  ComplexNumber div(ComplexNumber c) {
    return times(c.conjugate()).times(new ComplexNumber(1 / c.abs() / c.abs(), 0));
  }

  double abs() {
    return Math.sqrt(real * real + imag * imag);
  }

  ComplexNumber conjugate() {
    return new ComplexNumber(real, -imag);
  }

  ComplexNumber exponentialOf() {
    return new ComplexNumber(Math.exp(real), 0)
        .times(new ComplexNumber(Math.cos(imag), Math.sin(imag)));
  }
}
