use std::{collections::HashSet, hash::Hash};

#[derive(Debug, PartialEq, Eq)]
pub struct CustomSet<T: Eq + Hash> {
    set: HashSet<T>,
}

impl<T: Eq + Hash + Copy> CustomSet<T> {
    pub fn new(input: &[T]) -> Self {
        Self {
            set: HashSet::from_iter(input.iter().copied()),
        }
    }

    pub fn contains(&self, element: &T) -> bool {
        self.set.contains(element)
    }

    pub fn add(&mut self, element: T) {
        self.set.insert(element);
    }

    pub fn is_subset(&self, other: &Self) -> bool {
        self.set.is_subset(&other.set)
    }

    pub fn is_empty(&self) -> bool {
        self.set.is_empty()
    }

    pub fn is_disjoint(&self, other: &Self) -> bool {
        self.set.is_disjoint(&other.set)
    }

    #[must_use]
    pub fn intersection(&self, other: &Self) -> Self {
        Self {
            set: self.set.intersection(&other.set).copied().collect(),
        }
    }

    #[must_use]
    pub fn difference(&self, other: &Self) -> Self {
        Self {
            set: self.set.difference(&other.set).copied().collect(),
        }
    }

    #[must_use]
    pub fn union(&self, other: &Self) -> Self {
        Self {
            set: self.set.union(&other.set).copied().collect(),
        }
    }
}
