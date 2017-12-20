def collatz_steps(number):
    if number <= 0:
        return None

    step = 0
    while number != 1:
        number = (number // 2) if number % 2 == 0 else (3 * number + 1)
        step += 1
    return step
