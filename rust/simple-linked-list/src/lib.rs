use std::iter::FromIterator;

pub struct SimpleLinkedList<T> {
    head: Option<Box<Node<T>>>,
}

struct Node<T> {
    element: T,
    next: Option<Box<Node<T>>>,
}

impl<T> Node<T> {
    fn new(element: T, next: Option<Box<Node<T>>>) -> Self {
        Self { element, next }
    }
}

impl<T> SimpleLinkedList<T> {
    pub fn new() -> Self {
        Self { head: None }
    }

    // You may be wondering why it's necessary to have is_empty()
    // when it can easily be determined from len().
    // It's good custom to have both because len() can be expensive for some types,
    // whereas is_empty() is almost always cheap.
    // (Also ask yourself whether len() is expensive for SimpleLinkedList)
    pub fn is_empty(&self) -> bool {
        self.head.is_none()
    }

    pub fn len(&self) -> usize {
        let mut result = 0;
        let mut node = &self.head;
        while node.is_some() {
            node = &node.as_ref().unwrap().next;
            result += 1;
        }

        result
    }

    pub fn push(&mut self, element: T) {
        self.head = Some(Box::new(Node::new(element, self.head.take())));
    }

    pub fn pop(&mut self) -> Option<T> {
        if self.head.is_some() {
            let mut node = self.head.take();
            self.head = node.as_mut().unwrap().next.take();

            Some(node.unwrap().element)
        } else {
            None
        }
    }

    pub fn peek(&self) -> Option<&T> {
        match self.head.as_ref() {
            Some(node) => Some(&node.element),
            None => None,
        }
    }

    #[must_use]
    pub fn rev(mut self) -> SimpleLinkedList<T> {
        let mut result = Self::new();
        while let Some(element) = self.pop() {
            result.push(element);
        }

        result
    }
}

impl<T> FromIterator<T> for SimpleLinkedList<T> {
    fn from_iter<I: IntoIterator<Item = T>>(iter: I) -> Self {
        let mut result = Self::new();
        for element in iter {
            result.push(element);
        }

        result
    }
}

// In general, it would be preferable to implement IntoIterator for SimpleLinkedList<T>
// instead of implementing an explicit conversion to a vector. This is because, together,
// FromIterator and IntoIterator enable conversion between arbitrary collections.
// Given that implementation, converting to a vector is trivial:
//
// let vec: Vec<_> = simple_linked_list.into_iter().collect();
//
// The reason this exercise's API includes an explicit conversion to Vec<T> instead
// of IntoIterator is that implementing that interface is fairly complicated, and
// demands more of the student than we expect at this point in the track.

impl<T> From<SimpleLinkedList<T>> for Vec<T> {
    fn from(mut linked_list: SimpleLinkedList<T>) -> Vec<T> {
        let mut result = Vec::new();
        while let Some(element) = linked_list.pop() {
            result.push(element);
        }
        result.reverse();

        result
    }
}

impl<T> Default for SimpleLinkedList<T> {
    fn default() -> Self {
        Self::new()
    }
}
