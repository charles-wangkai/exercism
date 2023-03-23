pub fn nth(n: u32) -> u32 {
    let mut prime = 1;
    for _ in 0..=n {
        prime = compute_next_prime(prime)
    }

    prime
}

fn compute_next_prime(mut x: u32) -> u32 {
    loop {
        x += 1;
        if is_prime(x) {
            return x;
        }
    }
}

fn is_prime(x: u32) -> bool {
    let mut i = 2;
    while i * i <= x {
        if x % i == 0 {
            return false;
        }

        i += 1;
    }

    true
}
