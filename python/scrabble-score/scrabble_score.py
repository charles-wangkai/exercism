VALUE_TO_LETTERS = {
    1: ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'],
    2: ['D', 'G'],
    3: ['B', 'C', 'M', 'P'],
    4: ['F', 'H', 'V', 'W', 'Y'],
    5: ['K'],
    8: ['J', 'X'],
    10: ['Q', 'Z']
}

LETTER_TO_VALUE = {
    letter: value
    for value in VALUE_TO_LETTERS
    for letter in VALUE_TO_LETTERS[value]
}


def score(word):
    return sum(map(lambda letter: LETTER_TO_VALUE[letter], word.upper()))
