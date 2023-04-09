#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    SpanTooLong,
    InvalidDigit(char),
}

pub fn lsp(string_digits: &str, span: usize) -> Result<u64, Error> {
    if span > string_digits.len() {
        return Err(Error::SpanTooLong);
    }
    if let Some(c) = string_digits.chars().find(|c| !c.is_ascii_digit()) {
        return Err(Error::InvalidDigit(c));
    }

    Ok(if span == 0 {
        1
    } else {
        string_digits
            .as_bytes()
            .windows(span)
            .map(|window| window.iter().map(|c| (c - b'0') as u64).product())
            .max()
            .unwrap()
    })
}
