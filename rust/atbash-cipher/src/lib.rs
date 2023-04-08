/// "Encipher" with the Atbash cipher.
pub fn encode(plain: &str) -> String {
    let mut result = String::new();
    for c in plain.to_ascii_lowercase().chars() {
        if c.is_ascii_alphanumeric() {
            if result.len() % 6 == 5 {
                result.push(' ');
            }

            result.push(if c.is_ascii_alphabetic() {
                (b'a' + b'z' - c as u8) as char
            } else {
                c
            });
        }
    }

    result
}

/// "Decipher" with the Atbash cipher.
pub fn decode(cipher: &str) -> String {
    cipher
        .replace(' ', "")
        .chars()
        .map(|c| {
            if c.is_ascii_alphabetic() {
                (b'a' + b'z' - c as u8) as char
            } else {
                c
            }
        })
        .collect()
}
