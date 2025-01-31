import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Tree {
  private final String label;
  private final List<Tree> children;
  private Tree parent;

  public Tree(String label) {
    this(label, new ArrayList<>());
  }

  public Tree(String label, List<Tree> children) {
    this.label = label;
    this.children = children;
    for (Tree child : children) {
      child.parent = this;
    }
  }

  public static Tree of(String label) {
    return new Tree(label);
  }

  public static Tree of(String label, List<Tree> children) {
    return new Tree(label, children);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Tree other = (Tree) o;

    return label.equals(other.label)
        && children.size() == other.children.size()
        && children.containsAll(other.children);
  }

  @Override
  public int hashCode() {
    return Objects.hash(label, children);
  }

  @Override
  public String toString() {
    return "Tree{%s, %s}".formatted(label, children);
  }

  public Tree fromPov(String fromNode) {
    Map<String, Tree> labelToTree = buildLabelToTree();
    if (!labelToTree.containsKey(fromNode)) {
      throw new UnsupportedOperationException("Tree could not be reoriented");
    }

    return labelToTree.get(fromNode).buildTree(null);
  }

  public List<String> pathTo(String fromNode, String toNode) {
    Map<String, Tree> labelToTree = buildLabelToTree();
    if (!labelToTree.containsKey(fromNode) || !labelToTree.containsKey(toNode)) {
      throw new UnsupportedOperationException("No path found");
    }

    List<String> path = new ArrayList<>();
    fromPov(fromNode).find(toNode, path);

    return path;
  }

  private boolean find(String toNode, List<String> path) {
    path.add(label);

    if (label == toNode) {
      return true;
    }

    for (Tree child : children) {
      if (child.find(toNode, path)) {
        return true;
      }
    }

    path.removeLast();

    return false;
  }

  private Tree buildTree(Tree source) {
    List<Tree> children = new ArrayList<>();
    if (parent != null && parent != source) {
      children.add(parent.buildTree(this));
    }
    for (Tree child : this.children) {
      if (child != source) {
        children.add(child.buildTree(this));
      }
    }

    return new Tree(label, children);
  }

  private Map<String, Tree> buildLabelToTree() {
    Map<String, Tree> labelToTree = new HashMap<>();
    search(labelToTree);

    return labelToTree;
  }

  private void search(Map<String, Tree> labelToTree) {
    labelToTree.put(label, this);
    for (Tree child : children) {
      child.search(labelToTree);
    }
  }
}
