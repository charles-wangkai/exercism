import re


def abbreviate(words):
    return ''.join(map(lambda word: word[0].upper(), re.split(r' |-', words)))
