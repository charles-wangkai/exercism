use std::collections::HashSet;

use itertools::Itertools;

pub fn anagrams_for<'a>(word: &str, possible_anagrams: &[&'a str]) -> HashSet<&'a str> {
    let word = word.to_lowercase();
    let word_key = build_key(&word);

    possible_anagrams
        .iter()
        .copied()
        .filter(|&possible_anagram| {
            let possible_anagram = possible_anagram.to_lowercase();

            possible_anagram != word && build_key(&possible_anagram) == word_key
        })
        .collect()
}

fn build_key(s: &str) -> String {
    s.chars().sorted_unstable().collect()
}
