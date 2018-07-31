def saddle_points(matrix):
    row = len(matrix)
    if row == 0:
        return set()
    if len(set(map(len, matrix))) != 1:
        raise ValueError('Irregular Matrix!')
    col = len(matrix[0])

    return {(r, c)
            for r in range(row)
            for c in range(col)
            if matrix[r][c]
            == find_max_in_row(matrix, r)
            == find_min_in_colum(matrix, c)}


def find_max_in_row(matrix, r):
    return max(matrix[r])


def find_min_in_colum(matrix, c):
    return min([matrix[i][c] for i in range(len(matrix))])
