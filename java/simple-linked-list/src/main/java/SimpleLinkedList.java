import java.lang.reflect.Array;
import java.util.NoSuchElementException;

public class SimpleLinkedList<T> {
  Node<T> head;

  SimpleLinkedList() {}

  SimpleLinkedList(T[] values) {
    for (int i = values.length - 1; i >= 0; --i) {
      push(values[i]);
    }
  }

  int size() {
    int result = 0;
    for (Node<T> node = head; node != null; node = node.next) {
      ++result;
    }

    return result;
  }

  void push(T value) {
    Node<T> node = new Node<>(value);
    node.next = head;
    head = node;
  }

  T pop() {
    if (head == null) {
      throw new NoSuchElementException();
    }

    T result = head.value;
    head = head.next;

    return result;
  }

  void reverse() {
    SimpleLinkedList<T> reversed = new SimpleLinkedList<>();
    while (head != null) {
      reversed.push(pop());
    }
    head = reversed.head;
  }

  @SuppressWarnings("unchecked")
  <E> E[] asArray(Class<E> c) {
    E[] result = (E[]) Array.newInstance(c, size());
    Node<T> node = head;
    for (int i = 0; i < result.length; ++i) {
      result[i] = (E) node.value;
      node = node.next;
    }

    return result;
  }
}

class Node<T> {
  T value;
  Node<T> next;

  Node(T value) {
    this.value = value;
  }
}
