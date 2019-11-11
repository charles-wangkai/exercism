import collections
import functools
import itertools


UNIT_PRICE = 800

GROUP_SIZE_TO_DISCOUNT = {
    1: 0,
    2: 0.05,
    3: 0.1,
    4: 0.2,
    5: 0.25
}


def total(books):
    return calculate(to_tuple_key(collections.Counter(books)))


# Dynamic programming
@functools.lru_cache(maxsize=None)
def calculate(book_and_count_pairs):
    if not has_books(book_and_count_pairs):
        return 0

    return min([compute_group_price(group) + calculate(build_remaining_book_and_count_pairs(book_and_count_pairs, group))
                for group in generate_all_groups(len(book_and_count_pairs))
                if is_valid_group(group, book_and_count_pairs)])


def generate_all_groups(book_num):
    return itertools.product([0, 1], repeat=book_num)


def is_valid_group(group, book_and_count_pairs):
    return sum(group) > 0 and all(map(lambda i: book_and_count_pairs[i][1] >= group[i], range(len(group))))


def compute_group_price(group):
    return sum(group) * UNIT_PRICE * (1 - GROUP_SIZE_TO_DISCOUNT[sum(group)])


def has_books(book_and_count_pairs):
    return any(map(lambda book_and_count: book_and_count[1] > 0, book_and_count_pairs))


def build_remaining_book_and_count_pairs(book_and_count_pairs, group):
    return tuple((book_and_count_pairs[i][0], book_and_count_pairs[i][1] - group[i])
                 for i in range(len(group)))


def to_tuple_key(d):
    return tuple(sorted(d.items()))
