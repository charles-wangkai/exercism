def maximum_value(maximum_weight, items):
    result = -1
    for code in range(1 << len(items)):
        used = decode(len(items), code)

        weight_sum = sum(items[i]['weight']
                         for i in range(len(items)) if used[i])
        if weight_sum <= maximum_weight:
            value_sum = sum(items[i]['value']
                            for i in range(len(items)) if used[i])
            result = max(result, value_sum)

    return result


def decode(size, code):
    used = []
    for _ in range(size):
        used.append(code & 1 != 0)
        code >>= 1
    return used
