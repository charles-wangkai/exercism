use std::collections::HashMap;

pub fn can_construct_note(magazine: &[&str], note: &[&str]) -> bool {
    let magazine_word_to_count = build_word_to_count(magazine);
    let note_word_to_count = build_word_to_count(note);

    note_word_to_count
        .iter()
        .all(|(&word, &count)| magazine_word_to_count.get(word).copied().unwrap_or(0) >= count)
}

fn build_word_to_count<'a>(words: &[&'a str]) -> HashMap<&'a str, u32> {
    let mut result = HashMap::new();
    for &word in words {
        result
            .entry(word)
            .and_modify(|count| *count += 1)
            .or_insert(1);
    }

    result
}
