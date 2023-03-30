use std::collections::HashMap;

use lazy_static::lazy_static;

lazy_static! {
    static ref VALUE_TO_LETTERS: HashMap<u64, Vec<char>> = HashMap::from([
        (1, vec!['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T']),
        (2, vec!['D', 'G']),
        (3, vec!['B', 'C', 'M', 'P']),
        (4, vec!['F', 'H', 'V', 'W', 'Y']),
        (5, vec!['K']),
        (8, vec!['J', 'X']),
        (10, vec!['Q', 'Z'])
    ]);
    static ref LETTER_TO_VALUE: HashMap<char, u64> = HashMap::from_iter(
        VALUE_TO_LETTERS
            .iter()
            .flat_map(|(&value, letters)| letters.iter().map(move |&letter| (letter, value)))
    );
}

/// Compute the Scrabble score for a word.
pub fn score(word: &str) -> u64 {
    word.to_ascii_uppercase()
        .chars()
        .filter(|c| LETTER_TO_VALUE.contains_key(c))
        .map(|c| LETTER_TO_VALUE[&c])
        .sum()
}
