import math


def cipher_text(plain_text):
    return ' '.join([''.join([segment[i] if i < len(segment) else ' ' for segment in get_plain_text_segments(plain_text)]) for i in range(get_square_size(plain_text))])


def get_square_size(plain_text):
    return math.ceil(math.sqrt(len(get_normalized_plain_text(plain_text))))


def get_normalized_plain_text(plain_text):
    return ''.join(filter(str.isalnum, plain_text.lower()))


def get_plain_text_segments(plain_text):
    normalized_plain_text = get_normalized_plain_text(plain_text)
    square_size = get_square_size(plain_text)
    return [normalized_plain_text[i:i + square_size] for i in range(0, len(normalized_plain_text), square_size)]
