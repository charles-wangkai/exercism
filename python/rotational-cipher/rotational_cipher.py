import string


def rotate(text, key):
    return text.translate(str.maketrans(string.ascii_lowercase + string.ascii_uppercase, string.ascii_lowercase[key:] + string.ascii_lowercase[0:key] + string.ascii_uppercase[key:] + string.ascii_uppercase[0:key]))