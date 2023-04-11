use std::{
    cmp::Ordering,
    ops::{Add, Mul, Sub},
    str::FromStr,
};

use num_bigint::BigInt;

/// Type implementing arbitrary-precision decimal arithmetic
#[derive(Debug, PartialEq)]
pub struct Decimal {
    amount: BigInt,
    neg_scale: u32,
}

struct DecimalAlignment {
    self_amount: BigInt,
    other_amount: BigInt,
    max_neg_scale: u32,
}

impl Decimal {
    fn new(mut amount: BigInt, mut neg_scale: u32) -> Self {
        while neg_scale != 0 && &amount % 10 == 0.into() {
            amount /= 10;
            neg_scale -= 1;
        }

        Self { amount, neg_scale }
    }

    pub fn try_from(input: &str) -> Option<Self> {
        Some(Self::new(
            BigInt::from_str(&input.replace('.', "")).unwrap(),
            (input.len() - 1 - input.find('.').unwrap_or(input.len() - 1)) as u32,
        ))
    }

    fn align(&self, other: &Self) -> DecimalAlignment {
        let max_neg_scale = self.neg_scale.max(other.neg_scale);

        DecimalAlignment {
            self_amount: &self.amount * BigInt::from(10).pow(max_neg_scale - self.neg_scale),
            other_amount: &other.amount * BigInt::from(10).pow(max_neg_scale - other.neg_scale),
            max_neg_scale,
        }
    }
}

impl PartialOrd for Decimal {
    fn partial_cmp(&self, other: &Self) -> Option<Ordering> {
        let DecimalAlignment {
            self_amount,
            other_amount,
            ..
        } = self.align(other);

        Some(self_amount.cmp(&other_amount))
    }
}

impl Add for Decimal {
    type Output = Self;

    fn add(self, rhs: Self) -> Self {
        let DecimalAlignment {
            self_amount,
            other_amount,
            max_neg_scale,
        } = self.align(&rhs);

        Self::new(self_amount + other_amount, max_neg_scale)
    }
}

impl Sub for Decimal {
    type Output = Self;

    fn sub(self, rhs: Self) -> Self {
        let DecimalAlignment {
            self_amount,
            other_amount,
            max_neg_scale,
        } = self.align(&rhs);

        Self::new(self_amount - other_amount, max_neg_scale)
    }
}

impl Mul for Decimal {
    type Output = Self;

    fn mul(self, rhs: Self) -> Self {
        Self::new(self.amount * rhs.amount, self.neg_scale + rhs.neg_scale)
    }
}
