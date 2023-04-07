pub fn count(lines: &[&str]) -> u32 {
    let row = lines.len();
    if row == 0 {
        return 0;
    }
    let col = lines[0].len();

    let mut result = 0;
    for r1 in 0..row {
        for c1 in 0..col {
            for r2 in r1 + 1..row {
                for c2 in c1 + 1..col {
                    if is_rectangle(lines, r1, c1, r2, c2) {
                        result += 1;
                    }
                }
            }
        }
    }

    result
}

fn is_rectangle(lines: &[&str], r1: usize, c1: usize, r2: usize, c2: usize) -> bool {
    get_cell(lines, r1, c1) == '+'
        && get_cell(lines, r1, c2) == '+'
        && get_cell(lines, r2, c1) == '+'
        && get_cell(lines, r2, c2) == '+'
        && (c1 + 1..c2)
            .all(|c| "-+".contains(get_cell(lines, r1, c)) && "-+".contains(get_cell(lines, r2, c)))
        && (r1 + 1..r2)
            .all(|r| "|+".contains(get_cell(lines, r, c1)) && "|+".contains(get_cell(lines, r, c2)))
}

fn get_cell(lines: &[&str], r: usize, c: usize) -> char {
    lines[r].as_bytes()[c] as char
}
