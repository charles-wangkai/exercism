def slices(series, length):
    digits = list(map(lambda ch: ord(ch) - ord('0'), series))

    if length < 1 or length > len(digits):
        raise ValueError
    
    return list(map(lambda i: digits[i: i + length], range(0, len(digits) - length + 1)))
