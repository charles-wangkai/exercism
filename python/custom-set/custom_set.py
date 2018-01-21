class CustomSet(object):
    def __init__(self, elements=[]):
        self.element_set = set(elements)

    def isempty(self):
        return len(self.element_set) == 0

    def __contains__(self, element):
        return element in self.element_set

    def issubset(self, other):
        return self.element_set <= other.element_set

    def isdisjoint(self, other):
        return self.element_set.isdisjoint(other.element_set)

    def __eq__(self, other):
        return self.element_set == other.element_set

    def add(self, element):
        self.element_set.add(element)

    def intersection(self, other):
        return CustomSet(self.element_set & other.element_set)

    def __sub__(self, other):
        return CustomSet(self.element_set - other.element_set)

    def __add__(self, other):
        return CustomSet(self.element_set | other.element_set)
