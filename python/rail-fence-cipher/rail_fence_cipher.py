def fence_pattern(rails, message_size):
    pattern = [[] for _ in range(rails)]
    row = 0
    row_offset = 1
    for col in range(message_size):
        pattern[row].append(col)

        row += row_offset
        if not 0 <= row < len(pattern):
            row_offset *= -1
            row += row_offset * 2
    return pattern


def encode(message, rails):
    pattern = fence_pattern(rails, len(message))
    return ''.join([''.join([message[col] for col in line]) for line in pattern])


def decode(encoded_message, rails):
    pattern = fence_pattern(rails, len(encoded_message))
    result = [None] * len(encoded_message)
    index = 0
    for line in pattern:
        for col in line:
            result[col] = encoded_message[index]
            index += 1
    return ''.join(result)