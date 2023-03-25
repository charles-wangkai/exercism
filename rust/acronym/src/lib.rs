use regex::Regex;

pub fn abbreviate(phrase: &str) -> String {
    Regex::new(r"[^a-zA-Z0-9']+")
        .unwrap()
        .split(phrase)
        .filter(|word| !word.is_empty())
        .map(|word| {
            if word.chars().all(|c| c.is_ascii_uppercase()) {
                String::from(&word[..1])
            } else {
                format!(
                    "{}{}",
                    word.chars().next().unwrap().to_uppercase(),
                    word[1..]
                        .chars()
                        .filter(|c| c.is_ascii_uppercase())
                        .collect::<String>()
                )
            }
        })
        .collect()
}
