SIGNALS = ['wink', 'double blink', 'close your eyes', 'jump']


def commands(number):
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
