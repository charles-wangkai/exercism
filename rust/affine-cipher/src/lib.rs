/// While the problem description indicates a return status of 1 should be returned on errors,
/// it is much more common to return a `Result`, so we provide an error type for the result here.
#[derive(Debug, Eq, PartialEq)]
pub enum AffineCipherError {
    NotCoprime(i32),
}

const M: i32 = 26;

/// Encodes the plaintext using the affine cipher with key (`a`, `b`). Note that, rather than
/// returning a return code, the more common convention in Rust is to return a `Result`.
pub fn encode(plaintext: &str, a: i32, b: i32) -> Result<String, AffineCipherError> {
    check_validity(a)?;

    let mut result = String::new();
    for c in plaintext.to_ascii_lowercase().chars() {
        if c.is_ascii_alphanumeric() {
            if result.len() % 6 == 5 {
                result.push(' ');
            }

            result.push(if c.is_ascii_alphabetic() {
                ((a * (c as u8 - b'a') as i32 + b) % M + b'a' as i32) as u8 as char
            } else {
                c
            });
        }
    }

    Ok(result)
}

/// Decodes the ciphertext using the affine cipher with key (`a`, `b`). Note that, rather than
/// returning a return code, the more common convention in Rust is to return a `Result`.
pub fn decode(ciphertext: &str, a: i32, b: i32) -> Result<String, AffineCipherError> {
    check_validity(a)?;

    let mut result = String::new();
    for c in ciphertext.chars() {
        if c.is_ascii_alphanumeric() {
            result.push(if c.is_ascii_alphabetic() {
                ((mod_inv(a) * ((c as u8 - b'a') as i32 - b).rem_euclid(M)) % M + b'a' as i32) as u8
                    as char
            } else {
                c
            });
        }
    }

    Ok(result)
}

fn mod_inv(a: i32) -> i32 {
    (1..).find(|&i| i * a % M == 1).unwrap()
}

fn check_validity(a: i32) -> Result<(), AffineCipherError> {
    match gcd(a, M) {
        1 => Ok(()),
        _ => Err(AffineCipherError::NotCoprime(a)),
    }
}

fn gcd(x: i32, y: i32) -> i32 {
    if y == 0 {
        x
    } else {
        gcd(y, x % y)
    }
}
