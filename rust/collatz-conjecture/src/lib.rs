pub fn collatz(mut n: u64) -> Option<u64> {
    if n == 0 {
        return None;
    }

    let mut result = 0;
    while n != 1 {
        if n % 2 == 0 {
            n /= 2;
        } else {
            match n.checked_mul(3) {
                Some(x) => match x.checked_add(1) {
                    Some(y) => n = y,
                    None => return None,
                },
                None => return None,
            }
        }

        result += 1;
    }

    Some(result)
}
