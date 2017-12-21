class TreeNode(object):
    def __init__(self, value):
        self.value = value
        self.left = self.right = None


class BinarySearchTree(object):
    def __init__(self):
        self.root = None

    def add(self, value):
        node = TreeNode(value)

        if self.root is None:
            self.root = node
        else:
            parent = self.root
            while True:
                if value <= parent.value:
                    if parent.left is None:
                        parent.left = node
                        break
                    else:
                        parent = parent.left
                else:
                    if parent.right is None:
                        parent.right = node
                        break
                    else:
                        parent = parent.right

    def search(self, value):
        node = self.root
        while node is not None:
            if value == node.value:
                return node
            elif value < node.value:
                node = node.left
            else:
                node = node.right
        return None

    def list(self):
        result = []
        self.inorder(result, self.root)
        return result

    def inorder(self, result, node):
        if node is not None:
            self.inorder(result, node.left)
            result.append(node.value)
            self.inorder(result, node.right)
