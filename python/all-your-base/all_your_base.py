def rebase(from_base, digits, to_base):
    if from_base < 2 or to_base < 2:
        raise ValueError('Bases must be at least 2.')

    if any(map(lambda digit: not 0 <= digit < from_base, digits)):
        raise ValueError('Digits are out of bound.')

    number = 0
    for digit in digits:
        number = number * from_base + digit

    target_digits = []
    while number:
        target_digits.append(number % to_base)
        number //= to_base

    if not target_digits:
        target_digits.append(0)

    return target_digits[::-1]
