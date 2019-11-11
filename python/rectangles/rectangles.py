def rectangles(ascii_diagram):
    row = len(ascii_diagram)
    if row == 0:
        return 0
    col = len(ascii_diagram[0])

    return len([None for r1 in range(0, row) for c1 in range(0, col) for r2 in range(r1 + 1, row) for c2 in range(c1 + 1, col) if is_rectangle(ascii_diagram, r1, c1, r2, c2)])


def is_rectangle(grid, r1, c1, r2, c2):
    return (all(map(lambda p: grid[p[0]][p[1]] == '+', [(r1, c1), (r1, c2), (r2, c1), (r2, c2)]))
            and all(map(lambda c: grid[r1][c] in '-+' and grid[r2][c] in '-+', range(c1 + 1, c2)))
            and all(map(lambda r: grid[r][c1] in '|+' and grid[r][c2] in '|+', range(r1 + 1, r2))))
