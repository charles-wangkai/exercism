use std::fmt::{Display, Formatter, Result};

pub struct Roman(u32);

const VALUE_SYMBOLS: &[(u32, &str)] = &[
    (1000, "M"),
    (900, "CM"),
    (500, "D"),
    (400, "CD"),
    (100, "C"),
    (90, "XC"),
    (50, "L"),
    (40, "XL"),
    (10, "X"),
    (9, "IX"),
    (5, "V"),
    (4, "IV"),
    (1, "I"),
];

impl Display for Roman {
    fn fmt(&self, f: &mut Formatter<'_>) -> Result {
        let mut rest = self.0;
        let mut roman = String::new();
        for (value, symbol) in VALUE_SYMBOLS {
            while rest >= *value {
                roman.push_str(symbol);
                rest -= value;
            }
        }

        write!(f, "{}", roman)
    }
}

impl From<u32> for Roman {
    fn from(num: u32) -> Self {
        Self(num)
    }
}
