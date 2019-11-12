import math

M = 26


def encode(plain_text, a, b):
    check_validity(a)

    result = ''
    for ch in plain_text.lower():
        if ch.isalnum():
            if ch.isalpha():
                encoded = chr((a * (ord(ch) - ord('a')) + b) % M + ord('a'))
            else:
                encoded = ch

            if len(result) % 6 == 5:
                result += ' '
            result += encoded

    return result


def decode(ciphered_text, a, b):
    check_validity(a)

    result = ''
    for ch in ciphered_text:
        if ch.isalnum():
            if ch.isalpha():
                encoded = chr(mod_inv(a) * (ord(ch) - ord('a') - b % M + M) % M
                              + ord('a'))
            else:
                encoded = ch

            result += encoded

    return result


def mod_inv(a):
    result = 1
    while result * a % M != 1:
        result += 1

    return result


def check_validity(a):
    if math.gcd(a, M) != 1:
        raise ValueError('A and alphabet size (26) must be coprime!')
