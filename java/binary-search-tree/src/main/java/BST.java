import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST<T extends Comparable<? super T>> {
	static class Node<T> {
		T data;
		Node<T> left;
		Node<T> right;

		Node(T data, Node<T> left, Node<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		T getData() {
			return data;
		}

		Node<T> getLeft() {
			return left;
		}

		Node<T> getRight() {
			return right;
		}
	}

	Node<T> root;

	void insert(T data) {
		Node<T> node = new Node<T>(data, null, null);
		if (root == null) {
			root = node;
		} else {
			Node<T> parent = root;
			while (true) {
				if (data.compareTo(parent.data) <= 0) {
					if (parent.left == null) {
						parent.left = node;
						break;
					} else {
						parent = parent.left;
					}
				} else {
					if (parent.right == null) {
						parent.right = node;
						break;
					} else {
						parent = parent.right;
					}
				}
			}
		}
	}

	Node<T> getRoot() {
		return root;
	}

	List<T> getAsLevelOrderList() {
		List<T> result = new ArrayList<T>();
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<T> head = queue.poll();

			if (head == null) {
				continue;
			}

			result.add(head.data);
			queue.offer(head.left);
			queue.offer(head.right);
		}
		return result;
	}

	List<T> getAsSortedList() {
		List<T> result = new ArrayList<T>();
		inorder(result, root);
		return result;
	}

	void inorder(List<T> result, Node<T> node) {
		if (node == null) {
			return;
		}

		inorder(result, node.left);
		result.add(node.data);
		inorder(result, node.right);
	}
}
