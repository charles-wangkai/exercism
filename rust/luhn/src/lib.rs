/// Check a Luhn checksum.
pub fn is_valid(code: &str) -> bool {
    let code = code.replace(' ', "");

    code.len() >= 2
        && code.chars().all(|c| c.is_ascii_digit())
        && code
            .as_bytes()
            .iter()
            .rev()
            .map(|c| (c - b'0') as i32)
            .enumerate()
            .map(|(i, d)| if i % 2 == 0 { d } else { (d * 2 - 1) % 9 + 1 })
            .sum::<i32>()
            % 10
            == 0
}
