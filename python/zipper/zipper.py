LEFT, RIGHT = range(2)


class Zipper(object):
    def __init__(self, tree, breadcrumbs):
        self.tree = tree
        self.breadcrumbs = breadcrumbs

    @classmethod
    def from_tree(cls, tree, breadcrumbs=[]):
        return cls(tree, breadcrumbs) if tree else None

    def value(self):
        return self.tree['value']

    def set_value(self, value):
        return self.from_tree({'value': value, 'left': self.tree['left'], 'right': self.tree['right']}, self.breadcrumbs)

    def left(self):
        return self.from_tree(self.tree['left'], self.breadcrumbs + [(LEFT, self.tree['value'], self.tree['right'])])

    def set_left(self, left):
        return self.from_tree({'value': self.tree['value'], 'left': left, 'right': self.tree['right']}, self.breadcrumbs)

    def right(self):
        return self.from_tree(self.tree['right'], self.breadcrumbs + [(RIGHT, self.tree['value'], self.tree['left'])])

    def set_right(self, right):
        return self.from_tree({'value': self.tree['value'], 'left': self.tree['left'], 'right': right}, self.breadcrumbs)

    def up(self):
        direction, parent_value, other_subtree = self.breadcrumbs[-1]
        if direction == LEFT:
            return self.from_tree({'value': parent_value, 'left': self.tree, 'right': other_subtree}, self.breadcrumbs[:-1])
        else:
            return self.from_tree({'value': parent_value, 'left': other_subtree, 'right': self.tree}, self.breadcrumbs[:-1])

    def to_tree(self):
        if not self.breadcrumbs:
            return self.tree
        else:
            return self.up().to_tree()
