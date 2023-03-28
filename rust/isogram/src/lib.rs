use std::collections::HashSet;

pub fn check(candidate: &str) -> bool {
    let letters: Vec<_> = candidate
        .to_ascii_lowercase()
        .chars()
        .filter(|c| c.is_ascii_alphabetic())
        .collect();

    HashSet::<_>::from_iter(letters.iter()).len() == letters.len()
}
