pub fn is_armstrong_number(num: u32) -> bool {
    num.to_string()
        .as_bytes()
        .iter()
        .map(|c| ((c - b'0') as u64).pow(num.to_string().len() as u32))
        .sum::<u64>()
        == num as u64
}
