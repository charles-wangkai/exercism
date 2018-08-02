def append(xs, ys):
    xs_length = length(xs)
    ys_length = length(ys)

    result = [None] * (xs_length + ys_length)
    result[:xs_length] = xs
    result[xs_length:] = ys

    return result


def concat(lists):
    result = []
    for one_list in lists:
        result = append(result, one_list)
    return result


def filter_clone(function, xs):
    return [x for x in xs if function(x)]


def length(xs):
    result = 0
    for _ in xs:
        result += 1
    return result


def map_clone(function, xs):
    return [function(x) for x in xs]


def foldl(function, xs, acc):
    result = acc
    for x in xs:
        result = function(result, x)
    return result


def foldr(function, xs, acc):
    result = acc
    for x in reverse(xs):
        result = function(x, result)
    return result


def reverse(xs):
    xs_length = length(xs)

    result = [None] * xs_length
    i = 0
    while i != xs_length:
        result[i] = xs[xs_length - 1 - i]
        i += 1
    return result
