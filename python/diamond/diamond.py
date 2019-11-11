def rows(letter):
    width = (ord(letter) - ord('A')) * 2 + 1
    lines = [''.join([chr(i + ord('A')) if j in [width // 2 + i, width // 2 - i] else ' ' for j in range(width)])
             for i in range(ord(letter) - ord('A') + 1)]
    lines += lines[-2::-1]

    return lines
