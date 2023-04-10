use std::collections::HashMap;

/// Count occurrences of words.
pub fn word_count(words: &str) -> HashMap<String, u32> {
    let words_chars: Vec<_> = words.to_ascii_lowercase().chars().collect();
    let phrase = words_chars
        .iter()
        .enumerate()
        .map(|(i, &c)| {
            if c.is_ascii_alphanumeric()
                || (c == '\''
                    && i != 0
                    && words_chars[i - 1].is_ascii_alphanumeric()
                    && i != words_chars.len() - 1
                    && words_chars[i + 1].is_ascii_alphanumeric())
            {
                c
            } else {
                ' '
            }
        })
        .collect::<String>();

    let mut word_to_count = HashMap::new();
    for word in phrase.split_ascii_whitespace() {
        word_to_count
            .entry(String::from(word))
            .and_modify(|count| *count += 1)
            .or_insert(1);
    }

    word_to_count
}
