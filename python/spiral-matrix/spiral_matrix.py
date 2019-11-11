OFFSETS = [(0, 1), (1, 0), (0, -1), (-1, 0)]


def spiral_matrix(size):
    matrix = [[None] * size for _ in range(size)]

    r, c = 0, -1
    direction = 0
    for i in range(1, size * size + 1):
        while True:
            next_r, next_c = map(sum, zip((r, c), OFFSETS[direction]))

            if 0 <= next_r < size and 0 <= next_c < size and matrix[next_r][next_c] is None:
                break

            direction = (direction + 1) % len(OFFSETS)

        r, c = next_r, next_c
        matrix[r][c] = i

    return matrix
