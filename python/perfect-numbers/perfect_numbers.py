def classify(number):
    if number <= 0:
        raise ValueError

    aliquot_sum = compute_aliquot_sum(number)
    if aliquot_sum == number:
        return 'perfect'
    elif aliquot_sum > number:
        return 'abundant'
    else:
        return 'deficient'


def compute_aliquot_sum(n):
    if n == 1:
        return 0

    result = 1
    i = 2
    while i * i <= n:
        if n % i == 0:
            result += i
            if i * i != n:
                result += n // i
        i += 1
    return result
