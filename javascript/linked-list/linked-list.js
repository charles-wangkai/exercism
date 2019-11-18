//
// This is only a SKELETON file for the 'Linked List' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class LinkedList {
  constructor() {
    this.head = this.tail = null;
  }

  push(value) {
    const node = new Node(value);

    if (this.tail) {
      this.tail.next = node;
      node.prev = this.tail;
      this.tail = node;
    } else {
      this.head = this.tail = node;
    }
  }

  pop() {
    const result = this.tail.value;

    const newTail = this.tail.prev;
    if (newTail) {
      newTail.next = null;
      this.tail = newTail;
    } else {
      this.head = this.tail = null;
    }

    return result;
  }

  shift() {
    const result = this.head.value;

    const newHead = this.head.next;
    if (newHead) {
      newHead.prev = null;
      this.head = newHead;
    } else {
      this.head = this.tail = null;
    }

    return result;
  }

  unshift(value) {
    const node = new Node(value);

    if (this.head) {
      node.next = this.head;
      this.head.prev = node;
      this.head = node;
    } else {
      this.head = this.tail = node;
    }
  }

  delete(value) {
    let node = this.head;
    while (node && node.value != value) {
      node = node.next;
    }
    if (!node) {
      return;
    }

    if (node == this.head) {
      this.shift();
    } else if (node == this.tail) {
      this.pop();
    } else {
      const prevNode = node.prev;
      const nextNode = node.next;
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
    }
  }

  count() {
    let length = 0;
    let node = this.head;
    while (node) {
      length++;
      node = node.next;
    }

    return length;
  }
}

class Node {
  constructor(value, next, prev) {
    this.value = value;
    this.next = next;
    this.prev = prev;
  }
}
