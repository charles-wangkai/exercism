use std::cmp::Ordering;

#[derive(Debug, PartialEq, Eq)]
pub enum Classification {
    Abundant,
    Perfect,
    Deficient,
}

pub fn classify(num: u64) -> Option<Classification> {
    if num == 0 {
        return None;
    }

    match compute_aliquot_sum(num).cmp(&num) {
        Ordering::Less => Some(Classification::Deficient),
        Ordering::Greater => Some(Classification::Abundant),
        Ordering::Equal => Some(Classification::Perfect),
    }
}

fn compute_aliquot_sum(n: u64) -> u64 {
    if n == 1 {
        return 0;
    }

    let mut result = 1;
    let mut i = 2;
    while i * i <= n {
        if n % i == 0 {
            result += i;
            if i * i != n {
                result += n / i;
            }
        }

        i += 1;
    }

    result
}
