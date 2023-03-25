use std::collections::HashMap;

use itertools::Itertools;
use regex::Regex;

pub fn solve(input: &str) -> Option<HashMap<char, u8>> {
    let letters: Vec<_> = input
        .chars()
        .filter(|c| c.is_ascii_alphabetic())
        .unique()
        .collect();
    let items: Vec<_> = Regex::new(r"\W+").unwrap().split(input).collect();

    for digits in (0u8..10).permutations(letters.len()) {
        let letter_to_digit: HashMap<_, _> =
            (0..digits.len()).map(|i| (letters[i], digits[i])).collect();
        if check(&items, &letter_to_digit) {
            return Some(letter_to_digit);
        }
    }

    None
}

fn check(items: &[&str], letter_to_digit: &HashMap<char, u8>) -> bool {
    let mut values = Vec::new();
    for &item in items {
        if item.len() > 1 && letter_to_digit[&item.chars().next().unwrap()] == 0 {
            return false;
        }

        let mut value = 0;
        for letter in item.chars() {
            value = value * 10 + letter_to_digit[&letter] as u64;
        }
        values.push(value);
    }

    values[..values.len() - 1].iter().sum::<u64>() == values.last().copied().unwrap()
}
