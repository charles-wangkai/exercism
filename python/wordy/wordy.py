import re


def answer(question):
    fields = list(filter(None, re.split(r'What is|by|\?| ', question)))

    if len(fields) % 2 == 0:
        raise ValueError('Invalid question!')

    result = int(fields[0])
    for i in range(1, len(fields), 2):
        result = compute(result, fields[i], int(fields[i + 1]))

    return result


def compute(x, operation, y):
    if operation == 'plus':
        return x + y
    elif operation == 'minus':
        return x - y
    elif operation == 'multiplied':
        return x * y
    elif operation == 'divided':
        return x // y
    else:
        raise ValueError
