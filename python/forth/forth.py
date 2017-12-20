class StackUnderflowError(Exception):
    pass


def evaluate(input_data):
    stack = []
    name2definition = {}

    for command in input_data:
        command = command.lower()

        if command.startswith(': '):
            tokens = tokenize(command[2:-2])
            name = tokens[0]

            if is_number(name):
                raise ValueError('Cannot redefine numbers')

            definition = ' '.join(tokens[1:])
            name2definition[name] = definition
        else:
            for token in tokenize(replace(command, name2definition)):
                if is_number(token):
                    number = int(token)
                    stack.append(number)
                elif token == '+':
                    check_stack_size(stack, 2)

                    number1 = stack.pop()
                    number2 = stack.pop()
                    stack.append(number2 + number1)
                elif token == '-':
                    check_stack_size(stack, 2)

                    number1 = stack.pop()
                    number2 = stack.pop()
                    stack.append(number2 - number1)
                elif token == '*':
                    check_stack_size(stack, 2)

                    number1 = stack.pop()
                    number2 = stack.pop()
                    stack.append(number2 * number1)
                elif token == '/':
                    check_stack_size(stack, 2)

                    number1 = stack.pop()
                    number2 = stack.pop()
                    stack.append(number2 // number1)
                elif token == 'dup':
                    check_stack_size(stack, 1)

                    stack.append(stack[-1])
                elif token == 'drop':
                    check_stack_size(stack, 1)

                    stack.pop()
                elif token == 'swap':
                    check_stack_size(stack, 2)

                    number1 = stack.pop()
                    number2 = stack.pop()
                    stack.append(number1)
                    stack.append(number2)
                elif token == 'over':
                    check_stack_size(stack, 2)

                    stack.append(stack[-2])
                else:
                    raise ValueError('Undefined operator')
    return stack


def check_stack_size(stack, min_size):
    if len(stack) < min_size:
        raise StackUnderflowError(
            'The stack should contain at least {} value(s)'.format(min_size))


def replace(command, name2definition):
    return ' '.join(map(lambda token: name2definition.get(token, token), tokenize(command)))


def tokenize(s):
    return s.split()


def is_number(s):
    return s.isdigit()
