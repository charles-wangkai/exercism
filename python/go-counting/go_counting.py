OWNERS = [WHITE, BLACK, NONE] = 'W', 'B', ''

OFFSETS = [(0, -1), (1, 0), (0, 1), (-1, 0)]


class Board:
    def __init__(self, board):
        self.board = board

    def territory(self, x, y):
        row = len(self.board)
        col = len(self.board[0])

        if not (0 <= x < col and 0 <= y < row):
            raise ValueError('Coordinate is out of bounds!')

        if self.board[y][x] != ' ':
            return NONE, set()

        territory = set()
        stones = set()
        self.search(territory, stones, x, y)

        return next(iter(stones)) if len(stones) == 1 else NONE, territory

    def search(self, territory, stones, x, y):
        row = len(self.board)
        col = len(self.board[0])

        territory.add((x, y))

        for x_offset, y_offset in OFFSETS:
            adj_x, adj_y = x + x_offset, y + y_offset
            if 0 <= adj_x < col and 0 <= adj_y < row:
                if self.board[adj_y][adj_x] == ' ':
                    if (adj_x, adj_y) not in territory:
                        self.search(territory, stones, adj_x, adj_y)
                else:
                    stones.add(self.board[adj_y][adj_x])

    def territories(self):
        row = len(self.board)
        col = len(self.board[0])

        return {owner: {(x, y) for x in range(col) for y in range(row) if self.board[y][x] == ' ' and self.territory(x, y)[0] == owner} for owner in OWNERS}
