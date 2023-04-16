use lazy_static::lazy_static;
use std::collections::HashMap;

const STUDENTS: &[&str] = &[
    "Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph",
    "Kincaid", "Larry",
];
lazy_static! {
    static ref SYMBOL_TO_NAME: HashMap<char, &'static str> = HashMap::from([
        ('G', "grass"),
        ('C', "clover"),
        ('R', "radishes"),
        ('V', "violets")
    ]);
}

pub fn plants(diagram: &str, student: &str) -> Vec<&'static str> {
    let lines: Vec<Vec<char>> = diagram
        .split_ascii_whitespace()
        .map(|line| line.chars().collect())
        .collect();
    let index = STUDENTS.iter().position(|&s| s == student).unwrap();

    vec![
        SYMBOL_TO_NAME[&lines[0][index * 2]],
        SYMBOL_TO_NAME[&lines[0][index * 2 + 1]],
        SYMBOL_TO_NAME[&lines[1][index * 2]],
        SYMBOL_TO_NAME[&lines[1][index * 2 + 1]],
    ]
}
