pub fn translate(input: &str) -> String {
    input
        .split_ascii_whitespace()
        .map(translate_word)
        .collect::<Vec<_>>()
        .join(" ")
}

fn translate_word(word: &str) -> String {
    if starts_with_vowel(word) {
        return format!("{word}ay");
    }

    let first_consonant = get_first_consonant(word);

    format!("{}{first_consonant}ay", &word[first_consonant.len()..])
}

fn starts_with_vowel(word: &str) -> bool {
    ["yt", "xr", "a", "e", "i", "o", "u"]
        .iter()
        .any(|&vowel| word.starts_with(vowel))
}

fn get_first_consonant(word: &str) -> String {
    for multi_consonant in ["thr", "sch", "ch", "qu", "th", "rh"] {
        if word.starts_with(multi_consonant) {
            return String::from(multi_consonant);
        }
    }

    if word[1..].starts_with("qu") {
        return String::from(&word[..3]);
    }

    String::from(&word[..1])
}
