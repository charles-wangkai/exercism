def transpose(input_lines):
    lines = input_lines.splitlines()
    return '\n'.join([convert([lines[j][i] if i < len(lines[j]) else None for j in range(len(lines))]) for i in range(max(map(len, lines), default=0))])


def convert(r):
    index = len(r) - 1
    while index >= 0 and r[index] is None:
        index -= 1
    return ''.join(map(lambda ch: ' ' if ch is None else ch, r[:index + 1]))
