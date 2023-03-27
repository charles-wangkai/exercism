// This annotation prevents Clippy from warning us that `School` has a
// `fn new()` with no arguments, but doesn't implement the `Default` trait.
//
// Normally, it's good practice to just do what Clippy tells you, but in this
// case, we want to keep things relatively simple. The `Default` trait is not the point
// of this exercise.

use std::collections::HashMap;

use itertools::Itertools;

pub struct School {
    name_to_grade: HashMap<String, u32>,
}

impl School {
    #[allow(clippy::new_without_default)]
    pub fn new() -> Self {
        Self {
            name_to_grade: HashMap::new(),
        }
    }

    pub fn add(&mut self, grade: u32, student: &str) {
        self.name_to_grade.insert(String::from(student), grade);
    }

    pub fn grades(&self) -> Vec<u32> {
        self.name_to_grade
            .values()
            .unique()
            .sorted_unstable()
            .copied()
            .collect()
    }

    // If `grade` returned a reference, `School` would be forced to keep a `Vec<String>`
    // internally to lend out. By returning an owned vector of owned `String`s instead,
    // the internal structure can be completely arbitrary. The tradeoff is that some data
    // must be copied each time `grade` is called.
    pub fn grade(&self, grade: u32) -> Vec<String> {
        self.name_to_grade
            .iter()
            .filter(|(_, &g)| g == grade)
            .map(|(name, _)| name.clone())
            .sorted_unstable()
            .collect()
    }
}
