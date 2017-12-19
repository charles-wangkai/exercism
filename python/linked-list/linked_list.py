class Node(object):
    def __init__(self, value, next=None, previous=None):
        self.value = value
        self.next = next
        self.previous = previous


class LinkedList(object):
    def __init__(self):
        self.head = self.tail = None

    def __len__(self):
        length = 0
        node = self.head
        while node:
            length += 1
            node = node.next
        return length

    def __iter__(self):
        values = []
        node = self.head
        while node:
            values.append(node.value)
            node = node.next
        return iter(values)

    def push(self, value):
        node = Node(value)

        if self.tail:
            self.tail.next = node
            node.previous = self.tail
            self.tail = node
        else:
            self.head = self.tail = node

    def pop(self):
        result = self.tail.value
        new_tail = self.tail.previous
        if new_tail:
            new_tail.next = None
            self.tail = new_tail
        else:
            self.head = self.tail = None
        return result

    def shift(self):
        result = self.head.value
        new_head = self.head.next
        if new_head:
            new_head.previous = None
            self.head = new_head
        else:
            self.head = self.tail = None
        return result

    def unshift(self, value):
        node = Node(value)

        if self.head:
            node.next = self.head
            self.head.previous = node
            self.head = node
        else:
            self.head = self.tail = node
