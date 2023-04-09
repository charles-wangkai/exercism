use std::fmt::Display;

pub struct Luhn(String);

impl Luhn {
    pub fn is_valid(&self) -> bool {
        let code = self.0.replace(' ', "");

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
}

/// Here is the example of how the From trait could be implemented
/// for the &str type. Naturally, you can implement this trait
/// by hand for the every other type presented in the test suite,
/// but your solution will fail if a new type is presented.
/// Perhaps there exists a better solution for this problem?
impl<T: Display> From<T> for Luhn {
    fn from(input: T) -> Self {
        Self(input.to_string())
    }
}
