import math


def score(x, y):
    distance = math.sqrt(x * x + y * y)
    if distance <= 1:
        return 10
    elif distance <= 5:
        return 5
    elif distance <= 10:
        return 1
    else:
        return 0
