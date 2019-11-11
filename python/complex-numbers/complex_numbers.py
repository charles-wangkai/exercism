import cmath

ROUND_DIGIT_NUM = 15


class ComplexNumber(object):
    def __init__(self, real, imaginary):
        self.real = real
        self.imaginary = imaginary

    @classmethod
    def from_complex(cls, c):
        return ComplexNumber(round(c.real, ROUND_DIGIT_NUM), round(c.imag, ROUND_DIGIT_NUM))

    def __eq__(self, other):
        return self.real, self.imaginary == other.real, other.imaginary

    def __ne__(self, other):
        return self.real, self.imaginary != other.real, other.imaginary

    def __add__(self, other):
        return ComplexNumber.from_complex(complex(self.real, self.imaginary) + complex(other.real, other.imaginary))

    def __mul__(self, other):
        return ComplexNumber.from_complex(complex(self.real, self.imaginary) * complex(other.real, other.imaginary))

    def __sub__(self, other):
        return ComplexNumber.from_complex(complex(self.real, self.imaginary) - complex(other.real, other.imaginary))

    def __truediv__(self, other):
        return ComplexNumber.from_complex(complex(self.real, self.imaginary) / complex(other.real, other.imaginary))

    def __abs__(self):
        return abs(complex(self.real, self.imaginary))

    def conjugate(self):
        return ComplexNumber.from_complex(complex(self.real, self.imaginary).conjugate())

    def exp(self):
        return ComplexNumber.from_complex(cmath.exp(complex(self.real, self.imaginary)))
