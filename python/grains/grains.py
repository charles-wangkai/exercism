def square(integer_number):
    check_input(integer_number)
    return 1 << (integer_number - 1)


def total(integer_number=64):
    check_input(integer_number)
    return sum(map(square, range(1, integer_number + 1)))


def check_input(integer_number):
    if not 1 <= integer_number <= 64:
        raise ValueError('Invalid input!')
