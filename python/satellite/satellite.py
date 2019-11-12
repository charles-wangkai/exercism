def tree_from_traversals(preorder, inorder):
    if len(preorder) != len(inorder):
        raise ValueError('Traversal lengths should be same!')
    if len(preorder) != len(set(preorder)) or len(inorder) != len(set(inorder)):
        raise ValueError('Items in one traversal should be distinct!')

    return build(preorder, 0, len(preorder) - 1, inorder, 0, len(inorder) - 1)


def build(preorder, pre_begin_index, pre_end_index, inorder, in_begin_index, in_end_index):
    if pre_begin_index > pre_end_index:
        return {}

    in_index = in_begin_index
    while inorder[in_index] != preorder[pre_begin_index]:
        in_index += 1
        if in_index == len(inorder):
            raise ValueError('Inconsistent traversals!')

    return {
        'v': preorder[pre_begin_index],
        'l': build(preorder, pre_begin_index + 1, pre_begin_index + (in_index - in_begin_index), inorder, in_begin_index, in_index - 1),
        'r': build(preorder, pre_begin_index + (in_index - in_begin_index) + 1, pre_end_index, inorder, in_index + 1, in_end_index)
    }
