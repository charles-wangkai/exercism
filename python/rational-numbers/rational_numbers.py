from __future__ import division
import math


class Rational(object):
    def __init__(self, numerator, denominator):
        g = math.gcd(numerator, denominator)

        self.numerator = numerator // g
        self.denominator = denominator // g

        if self.denominator < 0:
            self.numerator *= -1
            self.denominator *= -1

    def __eq__(self, other):
        return self.numerator == other.numerator and self.denominator == other.denominator

    def __repr__(self):
        return '{}/{}'.format(self.numerator, self.denominator)

    def __add__(self, other):
        return Rational(self.numerator * other.denominator + other.numerator * self.denominator, self.denominator * other.denominator)

    def __sub__(self, other):
        return Rational(self.numerator * other.denominator - other.numerator * self.denominator, self.denominator * other.denominator)

    def __mul__(self, other):
        return Rational(self.numerator * other.numerator, self.denominator * other.denominator)

    def __truediv__(self, other):
        return Rational(self.numerator * other.denominator, self.denominator * other.numerator)

    def __abs__(self):
        return Rational(abs(self.numerator), self.denominator)

    def __pow__(self, power):
        if power < 0:
            r = self ** (-power)
            return Rational(r.denominator, r.numerator)

        result = Rational(1, 1)
        for _ in range(power):
            result *= self

        return result

    def __rpow__(self, base):
        return pow(pow(base, 1 / self.denominator), self.numerator)
