pub fn encrypt(input: &str) -> String {
    let normalized: Vec<_> = input
        .to_ascii_lowercase()
        .chars()
        .filter(|c| c.is_ascii_alphanumeric())
        .collect();
    let row = (normalized.len() as f64).sqrt().floor() as usize;
    let col = if row * row >= normalized.len() {
        row
    } else {
        row + 1
    };

    let mut rectangle = vec![vec![' '; col]; row];
    for i in 0..normalized.len() {
        rectangle[i / col][i % col] = normalized[i];
    }

    (0..col)
        .map(|c| (0..row).map(|r| rectangle[r][c]).collect::<String>())
        .collect::<Vec<_>>()
        .join(" ")
}
