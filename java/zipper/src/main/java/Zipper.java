public class Zipper {
	int value;
	Zipper left;
	Zipper right;
	Zipper up;

	Zipper(int value) {
		this.value = value;
	}

	int getValue() {
		return value;
	}

	void setValue(int value) {
		this.value = value;
	}

	void setLeft(Zipper left) {
		if (left != null) {
			left.up = this;
		}

		this.left = left;
	}

	void setRight(Zipper right) {
		if (right != null) {
			right.up = this;
		}

		this.right = right;
	}

	BinaryTree toTree() {
		Zipper root = this;
		while (root.up != null) {
			root = root.up;
		}
		return new BinaryTree(root);
	}
}
