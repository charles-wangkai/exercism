use itertools::Itertools;

pub fn chain(input: &[(u8, u8)]) -> Option<Vec<(u8, u8)>> {
    if input.is_empty() {
        return Some(Vec::new());
    }

    input
        .iter()
        .permutations(input.len())
        .map(|permutation| check(&permutation))
        .find(|solution| solution.is_some())
        .unwrap_or(None)
}

fn check(permutation: &[&(u8, u8)]) -> Option<Vec<(u8, u8)>> {
    let mut result = vec![*permutation[0]];
    let &(first, mut prev) = permutation[0];
    for &&(x, y) in &permutation[1..] {
        if x == prev {
            prev = y;
            result.push((x, y));
        } else if y == prev {
            prev = x;
            result.push((y, x));
        } else {
            return None;
        }
    }

    if prev == first {
        Some(result)
    } else {
        None
    }
}
