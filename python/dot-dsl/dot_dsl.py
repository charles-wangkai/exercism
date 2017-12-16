NODE, EDGE, ATTR = range(3)


class Node(object):
    def __init__(self, name, attrs={}):
        self.name = name
        self.attrs = attrs

    def __eq__(self, other):
        return self.name == other.name and self.attrs == other.attrs


class Edge(object):
    def __init__(self, src, dst, attrs={}):
        self.src = src
        self.dst = dst
        self.attrs = attrs

    def __eq__(self, other):
        return (self.src == other.src and
                self.dst == other.dst and
                self.attrs == other.attrs)


class Graph(object):
    def __init__(self, data=[]):
        if not isinstance(data, list):
            raise TypeError

        self.nodes = []
        self.edges = []
        self.attrs = {}

        for element in data:
            if len(element) == 0:
                raise TypeError

            if element[0] == NODE:
                if len(element) != 3:
                    raise ValueError

                self.nodes.append(Node(element[1], element[2]))
            elif element[0] == EDGE:
                if len(element) != 4:
                    raise ValueError

                self.edges.append(Edge(element[1], element[2], element[3]))
            elif element[0] == ATTR:
                if len(element) < 3:
                    raise TypeError
                elif len(element) > 3:
                    raise ValueError

                self.attrs[element[1]] = element[2]
            else:
                raise ValueError
