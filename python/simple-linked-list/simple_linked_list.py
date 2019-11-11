class Node(object):
    def __init__(self, value):
        self.v = value
        self.n = None

    def value(self):
        return self.v

    def next(self):
        return self.n


class LinkedList(object):
    def __init__(self, values=[]):
        self.h = None
        for value in values:
            self.push(value)

    def __len__(self):
        length = 0
        node = self.h
        while node:
            length += 1
            node = node.next()
        return length

    def __iter__(self):
        values = []
        node = self.h
        while node:
            values.append(node.v)
            node = node.next()
        return iter(values)

    def head(self):
        if self.h is None:
            raise EmptyListException('Empty list')

        return self.h

    def push(self, value):
        node = Node(value)
        node.n = self.h
        self.h = node

    def pop(self):
        result = self.head().v
        self.h = self.h.n
        return result

    def reversed(self):
        result = LinkedList()
        node = self.h
        while node:
            result.push(node.v)
            node = node.next()
        return result


class EmptyListException(Exception):
    pass