import collections
import functools
import itertools

PRICE = 8
RATIOS = [None, 1, 0.95, 0.9, 0.8, 0.75]


def calculate_total(books):
    return search(frozenset(collections.Counter(books).items()))


@functools.lru_cache(maxsize=None)
def search(book2count):
    if all(map(lambda bc: bc[1] == 0, book2count)):
        return 0

    book2count_list = list(book2count)
    return min([get_used_num(used) * PRICE * RATIOS[get_used_num(used)]
                + search(frozenset([(book2count_list[i][0], book2count_list[i][1] - (1 if used[i] else 0))
                                    for i in range(len(used))]))
                for used in itertools.product([0, 1], repeat=len(book2count))
                if get_used_num(used) > 0 and all(map(lambda i: not used[i] or book2count_list[i][1] > 0, range(len(used))))])


def get_used_num(used):
    return len([None for x in used if x])
