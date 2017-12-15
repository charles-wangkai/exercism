def saddle_points(matrix):
    row = len(matrix)
    if row == 0:
        return set()
    if len(set(map(len, matrix))) != 1:
        raise ValueError
    col = len(matrix[0])

    return set([(r, c) for r in range(row) for c in range(col) if matrix[r][c] == max(matrix[r]) == min([matrix[i][c] for i in range(row)])])
