pub fn get_diamond(c: char) -> Vec<String> {
    let mut result = Vec::new();
    let width = (c as u8 - b'A') * 2 + 1;
    for r in 0..c as u8 - b'A' + 1 {
        result.push(
            (0..width)
                .map(|c| {
                    if c == width / 2 - r || c == width / 2 + r {
                        (r + b'A') as char
                    } else {
                        ' '
                    }
                })
                .collect(),
        );
    }
    for i in (0..width / 2).rev() {
        result.push(String::from(&result[i as usize]));
    }

    result
}
