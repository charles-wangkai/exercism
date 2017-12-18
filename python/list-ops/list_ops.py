import functools


def append(xs, ys):
    return xs + ys


def concat(lists):
    return sum(lists, [])


def filter_clone(function, xs):
    return list(filter(function, xs))


def length(xs):
    return len(xs)


def map_clone(function, xs):
    return list(map(function, xs))


def foldl(function, xs, acc):
    return functools.reduce(function, xs, acc)


def foldr(function, xs, acc):
    return functools.reduce(lambda result, x: function(x, result), xs[::-1], acc)


def reverse(xs):
    return xs[::-1]
