use rand::Rng;

pub fn encode(key: &str, s: &str) -> Option<String> {
    if check_key(key) {
        Some(
            s.chars()
                .enumerate()
                .map(|(i, c)| {
                    (((c as u8 - b'a') + (key.chars().nth(i % key.len()).unwrap() as u8 - b'a'))
                        % 26
                        + b'a') as char
                })
                .collect(),
        )
    } else {
        None
    }
}

pub fn decode(key: &str, s: &str) -> Option<String> {
    if check_key(key) {
        Some(
            s.chars()
                .enumerate()
                .map(|(i, c)| {
                    ((c as i16 - key.chars().nth(i % key.len()).unwrap() as i16).rem_euclid(26)
                        as u8
                        + b'a') as char
                })
                .collect(),
        )
    } else {
        None
    }
}

pub fn encode_random(s: &str) -> (String, String) {
    let key = (0..100)
        .map(|_| rand::thread_rng().gen_range('a'..='z'))
        .collect::<String>();
    let encoded = encode(&key, s).unwrap();

    (key, encoded)
}

fn check_key(key: &str) -> bool {
    !key.is_empty() && key.chars().all(|c| c.is_ascii_lowercase())
}
