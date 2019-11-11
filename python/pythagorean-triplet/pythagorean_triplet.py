def triplets_with_sum(target_sum):
    result = []
    for a in range(1, target_sum // 3 + 1):
        for b in range(a, (target_sum - a) // 2 + 1):
            c = target_sum - a - b

            if a * a + b * b == c * c:
                result.append([a, b, c])

    return result
