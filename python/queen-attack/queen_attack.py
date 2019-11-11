class Queen:
    def __init__(self, r, c):
        if not (0 <= r < 8 and 0 <= c < 8):
            raise ValueError('Invalid position!')

        self.r = r
        self.c = c

    def can_attack(self, other):
        if self.r == other.r and self.c == other.c:
            raise ValueError(
                'Another queen can not be at the same position as mine!')

        return self.r == other.r \
            or self.c == other.c \
            or self.r + self.c == other.r + other.c \
            or self.r - self.c == other.r - other.c
