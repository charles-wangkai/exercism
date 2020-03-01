#if !defined(COMPLEX_NUMBERS_H)
#define COMPLEX_NUMBERS_H

#include <complex>

namespace complex_numbers
{
class Complex
{
private:
    std::complex<double> c;

public:
    Complex(const std::complex<double> &c);
    Complex(double real, double imag);
    double real() const;
    double imag() const;
    double abs() const;
    Complex conj() const;
    Complex exp() const;
    friend Complex operator+(const Complex &c1, const Complex &c2);
    friend Complex operator-(const Complex &c1, const Complex &c2);
    friend Complex operator*(const Complex &c1, const Complex &c2);
    friend Complex operator/(const Complex &c1, const Complex &c2);
};
} // namespace complex_numbers

#endif // COMPLEX_NUMBERS_H
