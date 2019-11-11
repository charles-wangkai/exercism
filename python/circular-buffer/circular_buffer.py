class BufferFullException(Exception):
    pass


class BufferEmptyException(Exception):
    pass


class CircularBuffer(object):
    def __init__(self, capacity):
        self.buffer = [None] * capacity
        self.clear()

    def read(self):
        if self.is_empty():
            raise BufferEmptyException('Buffer is empty!')

        result = self.buffer[self.head_index]
        self.head_index = (self.head_index + 1) % len(self.buffer)
        self.length -= 1
        return result

    def write(self, element):
        if self.is_full():
            raise BufferFullException('Buffer is full!')

        self.buffer[self.tail_index] = element
        self.tail_index = (self.tail_index + 1) % len(self.buffer)
        self.length += 1

    def overwrite(self, element):
        if self.is_full():
            self.read()
        self.write(element)

    def clear(self):
        self.head_index = 0
        self.tail_index = 0
        self.length = 0

    def is_empty(self):
        return self.length == 0

    def is_full(self):
        return self.length > 0 and self.head_index == self.tail_index
