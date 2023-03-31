const OFFSETS: &[(i32, i32)] = &[(0, 1), (1, 0), (0, -1), (-1, 0)];

pub fn spiral_matrix(size: u32) -> Vec<Vec<u32>> {
    let mut result = vec![vec![0; size as usize]; size as usize];
    let (mut r, mut c) = (0, -1);
    let mut direction = 0;
    for i in 1..=size * size {
        loop {
            let (next_r, next_c) = (r + OFFSETS[direction].0, c + OFFSETS[direction].1);
            if (0..size as i32).contains(&next_r)
                && (0..size as i32).contains(&next_c)
                && result[next_r as usize][next_c as usize] == 0
            {
                (r, c) = (next_r, next_c);
                result[next_r as usize][next_c as usize] = i;

                break;
            }

            direction = (direction + 1) % OFFSETS.len();
        }
    }

    result
}
