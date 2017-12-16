import re


def calculate(question):
    values = [int(field)
              for field in re.split(r'[^0-9+-]+', question) if len(field) > 0]

    if len(values) not in [2, 3]:
        raise ValueError

    parts = re.split(r'[0-9+-]+', question)

    if len(values) == 2:
        return compute(values[0], parts[1], values[1])
    else:
        return compute(compute(values[0], parts[1], values[1]), parts[2], values[2])


def compute(x, operation, y):
    if operation == ' plus ':
        return x + y
    elif operation == ' minus ':
        return x - y
    elif operation == ' multiplied by ':
        return x * y
    elif operation == ' divided by ':
        return x // y
    else:
        raise ValueError
