pub fn annotate(minefield: &[&str]) -> Vec<String> {
    let row = minefield.len();
    if row == 0 {
        return Vec::new();
    }
    let col = minefield[0].len();

    (0..row)
        .map(|r| {
            (0..col)
                .map(|c| {
                    if minefield[r].as_bytes()[c] == b'*' {
                        '*'
                    } else {
                        match (-1..=1i32)
                            .map(|dr| {
                                (-1..=1i32)
                                    .filter(|dc| {
                                        r as i32 + dr >= 0
                                            && r as i32 + dr < row as i32
                                            && c as i32 + dc >= 0
                                            && c as i32 + dc < col as i32
                                            && minefield[(r as i32 + dr) as usize].as_bytes()
                                                [(c as i32 + dc) as usize]
                                                == b'*'
                                    })
                                    .count()
                            })
                            .sum()
                        {
                            0 => ' ',
                            count => (b'0' + count as u8) as char,
                        }
                    }
                })
                .collect()
        })
        .collect()
}
