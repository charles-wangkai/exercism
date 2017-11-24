def on_square(integer_number):
    check_input(integer_number)
    return 1 << (integer_number - 1)


def total_after(integer_number):
    check_input(integer_number)
    return sum(map(on_square, range(1, integer_number + 1)))


def check_input(integer_number):
    if not 1 <= integer_number <= 64:
        raise ValueError
