IMAGES = [
    [' _ ',
     '| |',
     '|_|',
     '   '],
    ['   ',
     '  |',
     '  |',
     '   '],
    [' _ ',
     ' _|',
     '|_ ',
     '   '],
    [' _ ',
     ' _|',
     ' _|',
     '   '],
    ['   ',
     '|_|',
     '  |',
     '   '],
    [' _ ',
     '|_ ',
     ' _|',
     '   '],
    [' _ ',
     '|_ ',
     '|_|',
     '   '],
    [' _ ',
     '  |',
     '  |',
     '   '],
    [' _ ',
     '|_|',
     '|_|',
     '   '],
    [' _ ',
     '|_|',
     ' _|',
     '   ']
]


def convert(input_grid):
    row = len(input_grid)
    if row % 4 != 0:
        raise ValueError('Height should be divisible by 4!')

    col = len(input_grid[0])
    if col % 3 != 0:
        raise ValueError('Width should be divisible by 3!')

    return ','.join([''.join([recognize(input_grid, r, c) for c in range(0, col, 3)]) for r in range(0, row, 4)])


def recognize(lines, r, c):
    try:
        return str(IMAGES.index([lines[r][c:c + 3] for r in range(r, r + 4)]))
    except ValueError:
        return '?'
