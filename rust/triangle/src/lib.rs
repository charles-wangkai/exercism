use std::{fmt::Debug, ops::Add};

pub struct Triangle<T> {
    a: T,
    b: T,
    c: T,
}

impl<T> Triangle<T>
where
    T: PartialEq + Copy + Debug + PartialOrd + Add + TryFrom<u8>,
    T::Output: PartialOrd<T>,
    T::Error: Debug,
{
    pub fn build(sides: [T; 3]) -> Option<Self> {
        let mut sides = sides.to_vec();
        sides.sort_unstable_by(|x, y| x.partial_cmp(y).unwrap());

        let [a, b, c] = <[T; 3]>::try_from(sides).unwrap();

        if a > T::try_from(0).unwrap() && a + b > c {
            Some(Self { a, b, c })
        } else {
            None
        }
    }

    pub fn is_equilateral(&self) -> bool {
        self.a == self.c
    }

    pub fn is_scalene(&self) -> bool {
        !self.is_isosceles()
    }

    pub fn is_isosceles(&self) -> bool {
        self.a == self.b || self.b == self.c
    }
}
