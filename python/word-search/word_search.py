class Point(object):
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __repr__(self):
        return 'Point({}:{})'.format(self.x, self.y)

    def __add__(self, other):
        return Point(self.x + other.x, self.y + other.y)

    def __sub__(self, other):
        return Point(self.x - other.x, self.y - other.y)

    def __eq__(self, other):
        return self.x == other.x and self.y == other.y

    def __ne__(self, other):
        return not(self == other)


class WordSearch(object):
    def __init__(self, puzzle):
        self.square = puzzle.splitlines()

    def search(self, word):
        row = len(self.square)
        if row == 0:
            return None
        col = len(self.square[0])

        for x in range(0, col):
            for y in range(0, row):
                for r_offset in range(-1, 2):
                    for c_offset in range(-1, 2):
                        start = Point(x, y)
                        offset = Point(r_offset, c_offset)
                        if offset != Point(0, 0) and self.check(word, start, offset):
                            return start, start + Point(offset.x * (len(word) - 1), offset.y * (len(word) - 1))

    def check(self, word, start, offset):
        row = len(self.square)
        col = len(self.square[0])

        pos = start
        for i in range(len(word)):
            if not (0 <= pos.x < col and 0 <= pos.y < row and self.square[pos.y][pos.x] == word[i]):
                return False
            pos += offset
        return True
