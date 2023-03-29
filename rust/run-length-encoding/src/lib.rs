use std::str;

pub fn encode(source: &str) -> String {
    let mut result = String::new();
    let bytes = source.as_bytes();
    let mut prev = b'\0';
    let mut count = 0;
    for i in 0..=bytes.len() {
        if i != bytes.len() && bytes[i] == prev {
            count += 1;
        } else {
            if count != 0 {
                if count != 1 {
                    result.push_str(&count.to_string());
                }
                result.push(prev as char);
            }

            if i != bytes.len() {
                prev = bytes[i];
                count = 1;
            }
        }
    }

    result
}

pub fn decode(source: &str) -> String {
    let mut result = String::new();
    let bytes = source.as_bytes();
    let mut index = 0;
    while index < bytes.len() {
        let mut end_index = index;
        while end_index < bytes.len() && bytes[end_index].is_ascii_digit() {
            end_index += 1;
        }

        let count = if end_index == index {
            1
        } else {
            str::from_utf8(&bytes[index..end_index])
                .unwrap()
                .parse()
                .unwrap()
        };

        for _ in 0..count {
            result.push(bytes[end_index] as char);
        }

        index = end_index + 1;
    }

    result
}
