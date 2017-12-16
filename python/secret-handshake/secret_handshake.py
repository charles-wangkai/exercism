SIGNALS = ['wink', 'double blink', 'close your eyes', 'jump']


def handshake(number):
    try:
        n = number if isinstance(number, int) else int(number, 2)
    except ValueError:
        return []
    if n < 0:
        return []

    remain = n
    result = []
    for signal in SIGNALS:
        if remain & 1 != 0:
            result.append(signal)
        remain >>= 1
    if n >= 16:
        result = result[::-1]
    return result


def code(secret_code):
    try:
        values = [1 << SIGNALS.index(signal) for signal in secret_code]
    except ValueError:
        return '0'

    result = bin(sum(values))[2:]
    if len(values) > 1 and values[0] > values[1]:
        result = '1' + result
    return result
