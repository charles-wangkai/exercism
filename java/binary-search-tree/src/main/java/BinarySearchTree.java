import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<? super T>> {
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
    List<T> result = new ArrayList<>();
    Queue<Node<T>> queue = new ArrayDeque<>();
    if (root != null) {
      queue.offer(root);
    }
    while (!queue.isEmpty()) {
      Node<T> head = queue.poll();
      result.add(head.data);

      if (head.left != null) {
        queue.offer(head.left);
      }
      if (head.right != null) {
        queue.offer(head.right);
      }
    }

    return result;
  }

  List<T> getAsSortedList() {
    List<T> result = new ArrayList<>();
    inorder(result, root);

    return result;
  }

  void inorder(List<T> sorted, Node<T> node) {
    if (node != null) {
      inorder(sorted, node.left);
      sorted.add(node.data);
      inorder(sorted, node.right);
    }
  }
}
