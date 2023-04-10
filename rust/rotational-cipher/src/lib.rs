pub fn rotate(input: &str, key: i8) -> String {
    input
        .chars()
        .map(|c| {
            if c.is_ascii_lowercase() {
                ((c as i16 - 'a' as i16 + key as i16).rem_euclid(26) as u8 + b'a') as char
            } else if c.is_ascii_uppercase() {
                ((c as i16 - 'A' as i16 + key as i16).rem_euclid(26) as u8 + b'A') as char
            } else {
                c
            }
        })
        .collect()
}
