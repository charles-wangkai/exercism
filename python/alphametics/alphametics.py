import itertools
import re


def solve(puzzle):
    letters = list(set(filter(str.isalpha, puzzle)))
    items = re.split(r'\W+', puzzle)

    for digits in itertools.permutations(range(10), len(letters)):
        letter_to_digit = {letters[i]: digits[i] for i in range(len(letters))}
        if check(items, letter_to_digit):
            return letter_to_digit

    return None


def check(items, letter_to_digit):
    values = []
    for item in items:
        if len(item) > 1 and letter_to_digit[item[0]] == 0:
            return False

        value = 0
        for letter in item:
            value = value * 10 + letter_to_digit[letter]
        values.append(value)

    return sum(values[:-1]) == values[-1]
