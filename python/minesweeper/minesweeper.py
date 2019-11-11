def annotate(input_board_array):
    row = len(input_board_array)
    if row == 0:
        return []
    col = len(input_board_array[0])

    if len(set(map(len, input_board_array))) != 1:
        raise ValueError('Widths are not same!')
    if not all(map(lambda line: all(map(lambda square: square in '* ', line)), input_board_array)):
        raise ValueError('Invalid character!')

    board = [[None] * col for _ in range(row)]
    for r in range(row):
        for c in range(col):
            if input_board_array[r][c] == '*':
                board[r][c] = '*'
            else:
                count = len(list(filter(lambda adj: 0 <= adj[0] < row and 0 <= adj[1] < col and input_board_array[adj[0]][adj[1]] == '*',
                                        [(r + r_offset, c + c_offset) for r_offset in range(-1, 2) for c_offset in range(-1, 2)])))
                board[r][c] = ' ' if count == 0 else str(count)
    return [''.join(line) for line in board]
