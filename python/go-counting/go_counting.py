OWNERS = [WHITE, BLACK, NONE] = 'W', 'B', ''

OFFSETS = [(0, -1), (1, 0), (0, 1), (-1, 0)]


class Board:
    """Count territories of each player in a Go game

    Args:
        board (list[str]): A two-dimensional Go board
    """

    def __init__(self, board):
        self.board = board.splitlines()

    def territoryFor(self, coord):
        """Find the owner and the territories given a coordinate on
           the board

        Args:
            coord ((int,int)): Coordinate on the board

        Returns:
            (str, set): A tuple, the first element being the owner
                        of that area.  One of "W", "B", "".  The
                        second being a set of coordinates, representing
                        the owner's territories.
        """

        row = len(self.board)
        col = len(self.board[0])

        x, y = coord

        if (not (0 <= x < col and 0 <= y < row)) or self.board[y][x] != ' ':
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
        """Find the owners and the territories of the whole board

        Args:
            none

        Returns:
            dict(str, set): A dictionary whose key being the owner
                        , i.e. "W", "B", "".  The value being a set
                        of coordinates owned by the owner.
        """

        row = len(self.board)
        col = len(self.board[0])

        return {owner: {(x, y) for x in range(col) for y in range(row) if self.board[y][x] == ' ' and self.territoryFor((x, y))[0] == owner} for owner in OWNERS}
