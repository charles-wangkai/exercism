import re


def parse_markdown(markdown):
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
    return '<li>{}</li>'.format(parse_some_symbols(line[2:]))


def is_header(line):
    return line.startswith('#')


def parse_header(line):
    count = line.count('#')
    return '<h{}>{}</h{}>'.format(count, line[count + 1:], count)


def parse_paragraph(line):
    return '<p>{}</p>'.format(parse_some_symbols(line))


def parse_some_symbols(line):
    return re.sub(r'_(.+)_', r'<em>\1</em>', re.sub(r'__(.+)__', r'<strong>\1</strong>', line))
