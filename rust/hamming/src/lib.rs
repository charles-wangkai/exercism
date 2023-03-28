/// Return the Hamming distance between the strings,
/// or None if the lengths are mismatched.
pub fn hamming_distance(s1: &str, s2: &str) -> Option<usize> {
    if s1.len() == s2.len() {
        Some(
            s1.chars()
                .zip(s2.chars())
                .filter(|(c1, c2)| c1 != c2)
                .count(),
        )
    } else {
        None
    }
}
