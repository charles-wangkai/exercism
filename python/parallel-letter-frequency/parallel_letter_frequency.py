import collections
import multiprocessing.pool


def generate_counter(s):
    return collections.Counter(filter(str.isalpha, s.lower()))


def calculate(text_input):
    letter2count = collections.Counter()
    for counter in multiprocessing.pool.Pool().map(generate_counter, text_input):
        letter2count.update(counter)
    return letter2count
