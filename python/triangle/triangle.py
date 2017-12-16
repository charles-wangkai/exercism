class TriangleError(Exception):
    pass


class Triangle(object):
    def __init__(self, side_a, side_b, side_c):
        if min(side_a, side_b, side_c) <= 0 or side_a + side_b <= side_c or side_b + side_c <= side_a or side_c + side_a <= side_b:
            raise TriangleError

        self.side_a, self.side_b, self.side_c = side_a, side_b, side_c

    def kind(self):
        if self.side_a == self.side_b == self.side_c:
            return 'equilateral'
        elif self.side_a == self.side_b or self.side_b == self.side_c or self.side_c == self.side_a:
            return 'isosceles'
        else:
            return 'scalene'