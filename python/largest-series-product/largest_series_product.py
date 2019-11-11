import functools


def largest_product(series, size):
    if not all(map(str.isdigit, series)) or size > len(series) or size < 0:
        raise ValueError('Invalid input!')

    return max([functools.reduce(lambda x, y: x * y, [ord(series[j]) - ord('0') for j in range(i, i + size)], 1) for i in range(len(series) - size + 1)])
