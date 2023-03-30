pub fn find_saddle_points(input: &[Vec<u64>]) -> Vec<(usize, usize)> {
    (0..input.len())
        .flat_map(|r| {
            (0..input[r].len())
                .filter(move |&c| is_saddle_point(input, r, c))
                .map(move |c| (r, c))
        })
        .collect()
}

fn is_saddle_point(matrix: &[Vec<u64>], r: usize, c: usize) -> bool {
    matrix[r][c] == compute_max_in_row(matrix, r) && matrix[r][c] == compute_min_in_colum(matrix, c)
}

fn compute_max_in_row(matrix: &[Vec<u64>], r: usize) -> u64 {
    matrix[r].iter().max().copied().unwrap()
}

fn compute_min_in_colum(matrix: &[Vec<u64>], c: usize) -> u64 {
    (0..matrix.len()).map(|r| matrix[r][c]).min().unwrap()
}
