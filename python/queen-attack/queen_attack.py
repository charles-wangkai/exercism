def board(white_position, black_position):
    check(white_position, black_position)
    return [''.join(['W' if (r, c) == white_position else ('B' if (r, c) == black_position else '_') for c in range(8)]) for r in range(8)]


def can_attack(white_position, black_position):
    check(white_position, black_position)
    return white_position[0] == black_position[0] or white_position[1] == black_position[1] or white_position[0] + white_position[1] == black_position[0] + black_position[1] or white_position[0] - white_position[1] == black_position[0] - black_position[1]


def check(white_position, black_position):
    if white_position == black_position or any(map(lambda x: not 0 <= x < 8, white_position + black_position)):
        raise ValueError
