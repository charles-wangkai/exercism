def encode(numbers):
    return sum(map(encode_one_number, numbers), [])


def encode_one_number(number):
    s = bin(number)[2:]
    return [int(s[max(0, len(s) - i - 7):len(s) - i], 2) + (0 if i == 0 else 1 << 7) for i in range(0, len(s), 7)][::-1]


def decode(bytes_):
    if bytes_[-1] >= 1 << 7:
        raise ValueError('Incomplete sequence')

    result = []
    begin_index = 0
    for i in range(len(bytes_)):
        if bytes_[i] < 1 << 7:
            result.append(decode_one_number(bytes_[begin_index:i + 1]))
            begin_index = i + 1
    return result


def decode_one_number(bytes_):
    return int(''.join(map(lambda b: '{:0>7}'.format(bin(b % (1 << 7))[2:]), bytes_)), 2)