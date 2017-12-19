import itertools
import re


def solve(puzzle):
    letters = list(set(filter(str.isalpha, puzzle)))
    for digits in itertools.permutations(range(10), len(letters)):
        letter2digit = {letters[i]: digits[i] for i in range(len(letters))}
        if check(puzzle, letter2digit):
            return letter2digit
    return {}


def check(puzzle, letter2digit):
    items = [part.translate(str.maketrans({letter: str(digit) for (letter, digit) in letter2digit.items()}))
             for part in re.split(r'\W+', puzzle)]
    if any(map(lambda item: len(item) > 1 and item.startswith('0'), items)):
        return False
    return sum(map(int, items[:-1])) == int(items[-1])
