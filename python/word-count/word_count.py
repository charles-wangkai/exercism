import collections
import re


def count_words(phrase):
    return dict(collections.Counter(re.findall(r"[a-zA-Z0-9]+(?:'+[a-zA-Z0-9]+)*", phrase.lower())))
