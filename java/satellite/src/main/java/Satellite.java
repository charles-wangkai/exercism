import java.util.HashSet;
import java.util.List;

public class Satellite {
    public Tree treeFromTraversals(List<Character> preorder, List<Character> inorder) {
        if (preorder.size() != inorder.size()) {
            throw new IllegalArgumentException("traversals must have the same length");
        }
        if (!new HashSet<>(preorder).equals(new HashSet<>(inorder))) {
            throw new IllegalArgumentException("traversals must have the same elements");
        }
        if (preorder.stream().distinct().count() != preorder.size()
                || inorder.stream().distinct().count() != inorder.size()) {
            throw new IllegalArgumentException("traversals must contain unique items");
        }

        return new Tree(build(preorder, 0, preorder.size() - 1, inorder, 0, inorder.size() - 1));
    }

    Node build(List<Character> preorder, int preBeginIndex, int preEndIndex, List<Character> inorder, int inBeginIndex,
            int inEndIndex) {
        if (preBeginIndex > preEndIndex) {
            return null;
        }

        int inIndex = inBeginIndex;
        while (!inorder.get(inIndex).equals(preorder.get(preBeginIndex))) {
            ++inIndex;
        }

        return new Node(preorder.get(preBeginIndex),
                build(preorder, preBeginIndex + 1, preBeginIndex + (inIndex - inBeginIndex), inorder, inBeginIndex,
                        inIndex - 1),
                build(preorder, preBeginIndex + (inIndex - inBeginIndex) + 1, preEndIndex, inorder, inIndex + 1,
                        inEndIndex));
    }
}
