def is_armstrong_number(number):
    return number == sum([int(digit) ** len(str(number)) for digit in str(number)])
