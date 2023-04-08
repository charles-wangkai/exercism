pub fn series(digits: &str, len: usize) -> Vec<String> {
    (0..=digits.len() as i32 - len as i32)
        .map(|begin_index| String::from(&digits[begin_index as usize..begin_index as usize + len]))
        .collect()
}
