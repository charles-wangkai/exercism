pub struct PascalsTriangle {
    row_count: u32,
}

impl PascalsTriangle {
    pub fn new(row_count: u32) -> Self {
        Self { row_count }
    }

    pub fn rows(&self) -> Vec<Vec<u32>> {
        let mut result: Vec<Vec<_>> = Vec::new();
        for i in 0..self.row_count as usize {
            let mut row = Vec::new();
            row.push(1);
            for j in 1..i {
                row.push(result[i - 1][j - 1] + result[i - 1][j]);
            }
            if i != 0 {
                row.push(1);
            }

            result.push(row);
        }

        result
    }
}
