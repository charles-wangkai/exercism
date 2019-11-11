def grep(pattern, flags, files):
    result = ''
    for file in files:
        found = False
        with open(file) as fp:
            for line_num, line in enumerate(fp.readlines(), 1):
                line = line.rstrip('\n')
                if is_satisfied(line, pattern, flags):
                    if '-l' in flags:
                        if not found:
                            result += file + '\n'
                    else:
                        if len(files) > 1:
                            result += file + ':'
                        if '-n' in flags:
                            result += str(line_num) + ':'
                        result += line + '\n'
                    found = True
    return result


def is_satisfied(line, pattern, flags):
    match = len(line) == len(pattern) if '-x' in flags else True
    if match:
        if '-i' in flags:
            line = line.lower()
            pattern = pattern.lower()
        match = pattern in line
    return not match if '-v' in flags else match
