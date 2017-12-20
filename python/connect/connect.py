OFFSETS = [(-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0)]


class ConnectGame:
    def __init__(self, board):
        self.board = [line.replace(' ', '') for line in board.splitlines()]

    def get_winner(self):
        row = len(self.board)
        col = len(self.board[0])

        for c in range(col):
            if self.board[0][c] == 'O' and any(map(lambda cell: cell[0] == row - 1, self.find_connected_cells(0, c))):
                return 'O'

        for r in range(row):
            if self.board[r][0] == 'X' and any(map(lambda cell: cell[1] == col - 1, self.find_connected_cells(r, 0))):
                return 'X'

        return ''

    def find_connected_cells(self, r, c):
        connected_cells = set()
        self.search(connected_cells, r, c, self.board[r][c])
        return connected_cells

    def search(self, connected_cells, r, c, target):
        row = len(self.board)
        col = len(self.board[0])

        connected_cells.add((r, c))

        for r_offset, c_offset in OFFSETS:
            next_r, next_c = r + r_offset, c + c_offset
            if 0 <= next_r < row and 0 <= next_c < col and self.board[next_r][next_c] == target and (next_r, next_c) not in connected_cells:
                self.search(connected_cells, next_r, next_c, target)
