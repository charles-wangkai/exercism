import math


def primitive_triplets(number_in_triplet):
    if number_in_triplet % 4 != 0:
        raise ValueError

    return set([tuple(sorted([m * m - n * n, 2 * m * n, m * m + n * n])) for (m, n) in map(lambda n:(number_in_triplet // 2 // n, n), range(1, number_in_triplet // 2)) if 2 * m * n == number_in_triplet and m > n and (m - n) % 2 != 0 and math.gcd(m, n) == 1])


def triplets_in_range(range_start, range_end):
    return set([(a, b, c) for a in range(range_start, range_end + 1) for b in range(a, range_end + 1) for c in range(b, range_end + 1) if is_triplet((a, b, c))])


def is_triplet(triplet):
    a, b, c = sorted(triplet)
    return a * a + b * b == c * c
