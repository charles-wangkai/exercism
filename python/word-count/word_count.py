import collections
import re

def word_count(phrase):
    return dict(collections.Counter(filter(lambda word: len(word) > 0, re.split(r'[^a-zA-Z0-9]+', phrase.lower()))))
