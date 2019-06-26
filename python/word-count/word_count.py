import collections
import re


def count_words(phrase):
    return dict(collections.Counter(re.findall(r"[a-z0-9]+(?:'[a-z0-9]+)*", phrase.lower())))
