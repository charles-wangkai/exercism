public class DoublyLinkedList<T> {
	Node<T> head;
	Node<T> tail;

	void push(T value) {
		Node<T> node = new Node<T>(value);
		if (tail == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
			tail = node;
		}
	}

	T pop() {
		T result = tail.value;
		Node<T> newTail = tail.prev;
		if (newTail == null) {
			head = null;
			tail = null;
		} else {
			newTail.next = null;
			tail = newTail;
		}
		return result;
	}

	T shift() {
		T result = head.value;
		Node<T> newHead = head.next;
		if (newHead == null) {
			head = null;
			tail = null;
		} else {
			newHead.prev = null;
			head = newHead;
		}
		return result;
	}

	void unshift(T value) {
		Node<T> node = new Node<T>(value);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
	}
}

class Node<T> {
	Node<T> prev;
	Node<T> next;
	T value;

	Node(T value) {
		this.value = value;
	}
}