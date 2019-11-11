def is_paired(input_string):
    stack = []
    for ch in filter(lambda ch: ch in '()[]{}', input_string):
        if ch in '([{':
            stack.append(ch)
        elif len(stack) == 0 or (ch == ')' and stack[-1] != '(') or (ch == ']' and stack[-1] != '[') or (ch == '}' and stack[-1] != '{'):
            return False
        else:
            stack.pop()
    return len(stack) == 0
