use lazy_static::lazy_static;
use std::{collections::HashMap, sync::Mutex};

const DISCOUNTS: &[f64] = &[0.0, 0.0, 0.05, 0.1, 0.2, 0.25];

lazy_static! {
    static ref CACHE: Mutex<HashMap<String, u32>> = Mutex::new(HashMap::new());
}

pub fn lowest_price(books: &[u32]) -> u32 {
    let mut counts = [0u32; 5];
    for &book in books {
        counts[book as usize - 1] += 1;
    }

    search(counts)
}

fn search(counts: [u32; 5]) -> u32 {
    let key = counts
        .iter()
        .map(|count| count.to_string())
        .collect::<Vec<_>>()
        .join(",");

    if !CACHE.lock().unwrap().contains_key(&key) {
        let cost = if counts.iter().all(|&count| count == 0) {
            0
        } else {
            (1u32..1 << counts.len())
                .filter(|mask| {
                    counts
                        .iter()
                        .enumerate()
                        .all(|(i, &count)| count != 0 || ((mask >> i) & 1) == 0)
                })
                .map(|mask| {
                    ((mask.count_ones() * 800) as f64
                        * (1.0 - DISCOUNTS[mask.count_ones() as usize])) as u32
                        + search(
                            counts
                                .iter()
                                .enumerate()
                                .map(|(i, &count)| count - ((mask >> i) & 1))
                                .collect::<Vec<_>>()
                                .try_into()
                                .unwrap(),
                        )
                })
                .min()
                .unwrap()
        };
        CACHE.lock().unwrap().insert(key.clone(), cost);
    }

    CACHE.lock().unwrap()[&key]
}
