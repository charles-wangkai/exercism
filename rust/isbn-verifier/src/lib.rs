/// Determines whether the supplied string is a valid ISBN number
pub fn is_valid_isbn(isbn: &str) -> bool {
    let values: Vec<_> = isbn
        .replace('-', "")
        .chars()
        .map(|c| {
            if c == 'X' {
                Some(10)
            } else if c.is_ascii_digit() {
                Some(c as u8 - b'0')
            } else {
                None
            }
        })
        .collect();

    values.len() == 10
        && values
            .iter()
            .enumerate()
            .all(|(i, value)| value.is_some() && (i == 9 || value.unwrap() != 10))
        && values
            .iter()
            .enumerate()
            .map(|(i, value)| (10 - i) * value.unwrap() as usize)
            .sum::<usize>()
            % 11
            == 0
}
