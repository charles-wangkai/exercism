class SgfTree(object):
    def __init__(self, properties=None, children=None):
        self.properties = properties or {}
        self.children = children or []

    def __eq__(self, other):
        if not isinstance(other, SgfTree):
            return False
        for k, v in self.properties.items():
            if k not in other.properties:
                return False
            if other.properties[k] != v:
                return False
        for k in other.properties.keys():
            if k not in self.properties:
                return False
        if len(self.children) != len(other.children):
            return False
        for a, b in zip(self.children, other.children):
            if a != b:
                return False
        return True


def parse(input_string):
    input_string = input_string.replace('\n', ' ').replace('\t', ' ')
    if not (input_string.startswith('(') and input_string.endswith(')')):
        raise ValueError('Must enclosed with parentheses!')

    s = input_string[1:-1]
    if not s.startswith(';'):
        raise ValueError('Tree with no nodes!')

    s = s[1:]

    if not s:
        return SgfTree()

    begin_index = find_index(s, '[')
    if begin_index < 0:
        raise ValueError('Properties without delimiter!')

    property_id = s[:begin_index]
    if not property_id.isupper():
        raise ValueError('Property id must be uppercase!')

    properties = {property_id: []}
    while begin_index < len(s) and s[begin_index] == '[':
        end_index = find_index(s, ']', begin_index)
        properties[property_id].append(decode(s[begin_index + 1:end_index]))

        begin_index = end_index + 1

    s = s[begin_index:]

    children = []
    index = 0
    while index < len(s):
        if s[index] == ';':
            end_index = find_index(s, ';(', index + 1)
            if end_index >= 0:
                end_index -= 1
            else:
                end_index = len(s) - 1

            children.append(parse('(' + s[index:end_index + 1] + ')'))
        elif s[index] == '(':
            end_index = find_index(s, ')', index)
            children.append(parse(s[index:end_index + 1]))

        index = end_index + 1

    return SgfTree(properties, children)


def find_index(s, target, offset=0):
    index = offset
    while index < len(s):
        if s[index] in target:
            return index

        if s[index] == '\\':
            index += 2
        else:
            index += 1
    return -1


def decode(s):
    return s.replace('\\', '')
