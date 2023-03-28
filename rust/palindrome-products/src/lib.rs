/// `Palindrome` is a newtype which only exists when the contained value is a palindrome number in base ten.
///
/// A struct with a single field which is used to constrain behavior like this is called a "newtype", and its use is
/// often referred to as the "newtype pattern". This is a fairly common pattern in Rust.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub struct Palindrome(u64);

impl Palindrome {
    /// Create a `Palindrome` only if `value` is in fact a palindrome when represented in base ten. Otherwise, `None`.
    pub fn new(value: u64) -> Option<Palindrome> {
        if value
            .to_string()
            .chars()
            .rev()
            .collect::<String>()
            .parse::<u64>()
            .unwrap()
            == value
        {
            Some(Palindrome(value))
        } else {
            None
        }
    }

    /// Get the value of this palindrome.
    pub fn into_inner(self) -> u64 {
        self.0
    }
}

pub fn palindrome_products(min: u64, max: u64) -> Option<(Palindrome, Palindrome)> {
    let mut min_palindrome = None;
    let mut max_palindrome = None;
    for i in min..=max {
        for j in min..=max {
            if let Some(value) = Palindrome::new(i * j) {
                if min_palindrome.is_none() || value.into_inner() < min_palindrome.unwrap() {
                    min_palindrome = Some(value.into_inner());
                }
                if max_palindrome.is_none() || value.into_inner() > max_palindrome.unwrap() {
                    max_palindrome = Some(value.into_inner());
                }
            }
        }
    }

    min_palindrome.map(|min_palindrome| {
        (
            Palindrome::new(min_palindrome).unwrap(),
            Palindrome::new(max_palindrome.unwrap()).unwrap(),
        )
    })
}
