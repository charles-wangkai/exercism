#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    IncompleteNumber,
    Overflow,
}

/// Convert a list of numbers to a stream of bytes encoded with variable length encoding.
pub fn to_bytes(values: &[u32]) -> Vec<u8> {
    let mut result = Vec::new();
    for &value in values {
        let mut bytes = Vec::new();
        let mut rest = value;
        while rest != 0 {
            bytes.push((rest % (1 << 7) + if rest == value { 0 } else { 1 << 7 }) as u8);

            rest >>= 7;
        }
        if bytes.is_empty() {
            bytes.push(0);
        }

        bytes.reverse();
        result.append(&mut bytes);
    }

    result
}

/// Given a stream of bytes, extract all numbers which are encoded in there.
pub fn from_bytes(bytes: &[u8]) -> Result<Vec<u32>, Error> {
    if bytes.last().copied().unwrap() >= 1 << 7 {
        return Err(Error::IncompleteNumber);
    }

    let mut result = Vec::new();
    let mut value: u64 = 0;
    for &byte in bytes {
        value = (value << 7) + byte as u64 % (1 << 7);
        if value > u32::MAX.into() {
            return Err(Error::Overflow);
        }

        if byte < 1 << 7 {
            result.push(value as u32);
            value = 0;
        }
    }

    Ok(result)
}
