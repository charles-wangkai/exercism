def verify(isbn):
    s = isbn.replace('-', '')

    return len(s) == 10 and all(map(str.isdigit, s[:9])) and (s[9] == 'X' or s[9].isdigit()) and sum(map(lambda i: parse_int(s[i]) * (10 - i), range(10))) % 11 == 0


def parse_int(ch):
    return 10 if ch == 'X' else (ord(ch) - ord('0'))
