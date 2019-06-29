import re


def abbreviate(words):
    return ''.join(word[0].upper() for word in re.split(r"[^a-zA-Z0-9']+", words))
