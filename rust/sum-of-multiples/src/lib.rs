use itertools::{unfold, Itertools};

pub fn sum_of_multiples(limit: u32, factors: &[u32]) -> u32 {
    factors
        .iter()
        .filter(|&factor| *factor != 0)
        .flat_map(|factor| {
            unfold(*factor, |x| {
                let result = *x;
                *x += *factor;

                Some(result)
            })
            .take_while(|x| *x < limit)
        })
        .unique()
        .sum()
}
