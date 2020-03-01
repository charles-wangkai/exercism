#include "complex_numbers.h"

namespace complex_numbers
{
Complex::Complex(const std::complex<double> &c) : c{c} {}

Complex::Complex(double real, double imag) : c{real, imag} {}

double Complex::real() const { return c.real(); }

double Complex::imag() const { return c.imag(); }

double Complex::abs() const { return std::abs(c); }

Complex Complex::conj() const { return std::conj(c); };

Complex Complex::exp() const { return std::exp(c); };

Complex operator+(const Complex &c1, const Complex &c2) { return c1.c + c2.c; }

Complex operator-(const Complex &c1, const Complex &c2) { return c1.c - c2.c; }

Complex operator*(const Complex &c1, const Complex &c2) { return c1.c * c2.c; }

Complex operator/(const Complex &c1, const Complex &c2) { return c1.c / c2.c; }
} // namespace complex_numbers
