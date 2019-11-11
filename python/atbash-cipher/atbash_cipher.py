import string


def encode(plain_text):
    s = ''.join(filter(str.isalnum, plain_text.lower())).translate(str.maketrans(string.ascii_lowercase, string.ascii_lowercase[::-1]))
    return ' '.join([s[i:i + 5] for i in range(0, len(s), 5)])


def decode(ciphered_text):
    return ciphered_text.replace(' ', '').translate(str.maketrans(string.ascii_lowercase, string.ascii_lowercase[::-1]))