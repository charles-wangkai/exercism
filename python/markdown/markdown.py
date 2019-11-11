import re


def parse(markdown):
    res = ''
    in_list = False
    for line in markdown.splitlines():
        if is_list_item(line):
            if not in_list:
                res += '<ul>'
                in_list = True

            parsed_line = parse_list_item(line)
        else:
            if in_list:
                res += '</ul>'
                in_list = False

            if is_header(line):
                parsed_line = parse_header(line)
            else:
                parsed_line = parse_paragraph(line)

        res += parsed_line
    if in_list:
        res += '</ul>'
    return res


def is_list_item(line):
    return line.startswith('* ')


def parse_list_item(line):
    return tagger('li', parse_some_symbols(line[2:]))


def is_header(line):
    return line.startswith('#')


def parse_header(line):
    count = 0
    for i in range(len(line)):
        if line[i] != '#':
            break

        count += 1

    return tagger(f'h{count}', line[count + 1:])


def parse_paragraph(line):
    return tagger('p', parse_some_symbols(line))


def parse_some_symbols(line):
    return re.sub(r'_(.+)_', tagger('em', r'\1'), re.sub(r'__(.+)__', tagger('strong', r'\1'), line))


def tagger(tag, content):
    return f'<{tag}>{content}</{tag}>'
