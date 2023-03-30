pub fn primes_up_to(upper_bound: u64) -> Vec<u64> {
    let mut primes = vec![true; (upper_bound + 1) as usize];
    for i in 2..primes.len() {
        if primes[i] {
            for prime in primes.iter_mut().skip(i * i).step_by(i) {
                *prime = false;
            }
        }
    }

    (2..primes.len() as u64)
        .filter(|&i| primes[i as usize])
        .collect()
}
