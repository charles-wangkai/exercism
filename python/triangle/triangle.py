def equilateral(sides):
    a, b, c = sides
    return is_triangle(sides) and a == b == c


def isosceles(sides):
    a, b, c = sides
    return is_triangle(sides) and (a == b or b == c or c == a)


def scalene(sides):
    return is_triangle(sides) and not isosceles(sides)


def is_triangle(sides):
    a, b, c = sorted(sides)
    return a > 0 and a + b > c
