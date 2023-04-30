public class BinaryTree {
  Zipper root;

  BinaryTree(Zipper root) {
    this.root = root;
  }

  BinaryTree(int value) {
    root = new Zipper(value);
  }

  Zipper getRoot() {
    return root;
  }

  @Override
  public boolean equals(Object obj) {
    BinaryTree other = (BinaryTree) obj;

    return root.equals(other.root);
  }

  String printTree() {
    return print(root);
  }

  String print(Zipper zipper) {
    return (zipper == null)
        ? "null"
        : String.format(
            "value: %d, left: %s, right: %s",
            zipper.value, wrap(print(zipper.left)), wrap(print(zipper.right)));
  }

  String wrap(String s) {
    return s.equals("null") ? s : ("{ " + s + " }");
  }
}
