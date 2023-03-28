use std::collections::HashSet;

/// Determine whether a sentence is a pangram.
pub fn is_pangram(sentence: &str) -> bool {
    HashSet::<_>::from_iter(
        sentence
            .to_ascii_lowercase()
            .chars()
            .filter(|c| c.is_ascii_alphabetic()),
    )
    .len()
        == 26
}
