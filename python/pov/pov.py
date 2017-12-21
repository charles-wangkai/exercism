from json import dumps


class Tree(object):
    def __init__(self, label, children=[]):
        self.label = label
        self.children = children

        self.parent = None
        for child in children:
            child.parent = self

    def __dict__(self):
        return {self.label: [c.__dict__() for c in sorted(self.children)]}

    def __str__(self, indent=None):
        return dumps(self.__dict__(), indent=indent)

    def __lt__(self, other):
        return self.label < other.label

    def __eq__(self, other):
        return self.__dict__() == other.__dict__()

    def fromPov(self, from_node):
        label2tree = self.build_label2tree()
        if from_node not in label2tree:
            raise ValueError('Tree does not exist')

        return label2tree[from_node].build_tree(None)

    def build_tree(self, source):
        children = []
        if self.parent and (source is None or self.parent != source):
            children.append(self.parent.build_tree(self))
        for child in self.children:
            if source is None or source != child:
                children.append(child.build_tree(self))
        return Tree(self.label, children)

    def pathTo(self, from_node, to_node):
        label2tree = self.build_label2tree()
        if from_node not in label2tree or to_node not in label2tree:
            raise ValueError('Tree does not exist')

        path = []
        self.fromPov(from_node).find(path, to_node)
        return path

    def find(self, path, to_node):
        path.append(self.label)

        if self.label == to_node:
            return True

        for child in self.children:
            if child.find(path, to_node):
                return True

        path.pop()
        return False

    def build_label2tree(self):
        label2tree = {}
        self.search(label2tree)
        return label2tree

    def search(self, label2tree):
        if self.label in label2tree:
            return

        label2tree[self.label] = self
        if self.parent:
            self.parent.search(label2tree)
        for child in self.children:
            child.search(label2tree)
