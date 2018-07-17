public class DoublyLinkedList<T> {
	Node<T> head;
	Node<T> tail;

	void push(T value) {
		Node<T> node = new Node<T>(value, tail, null);
		if (tail == null) {
			head = node;
		} else {
			tail.next = node;
		}
		tail = node;
	}

	T pop() {
		T result = tail.value;
		Node<T> newTail = tail.prev;
		if (newTail == null) {
			head = null;
		} else {
			newTail.next = null;
		}
		tail = newTail;
		return result;
	}

	T shift() {
		T result = head.value;
		Node<T> newHead = head.next;
		if (newHead == null) {
			tail = null;
		} else {
			newHead.prev = null;
		}
		head = newHead;
		return result;
	}

	void unshift(T value) {
		Node<T> node = new Node<T>(value, null, head);
		if (head == null) {
			tail = node;
		} else {
			head.prev = node;
		}
		head = node;
	}
}

class Node<T> {
	T value;
	Node<T> prev;
	Node<T> next;

	Node(T value, Node<T> prev, Node<T> next) {
		this.value = value;
		this.prev = prev;
		this.next = next;
	}
}