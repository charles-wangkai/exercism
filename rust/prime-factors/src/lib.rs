pub fn factors(mut n: u64) -> Vec<u64> {
    let mut result = Vec::new();
    let mut i = 2;
    while i * i <= n {
        while n % i == 0 {
            result.push(i);
            n /= i;
        }

        i += 1;
    }
    if n != 1 {
        result.push(n);
    }

    result
}
