pub struct Item {
    pub weight: u32,
    pub value: u32,
}

pub fn maximum_value(max_weight: u32, items: Vec<Item>) -> u32 {
    (0..1 << items.len())
        .filter(|mask| {
            items
                .iter()
                .enumerate()
                .filter(|(i, _)| ((mask >> i) & 1) == 1)
                .map(|(_, Item { weight, .. })| weight)
                .sum::<u32>()
                <= max_weight
        })
        .map(|mask| {
            items
                .iter()
                .enumerate()
                .filter(|(i, _)| ((mask >> i) & 1) == 1)
                .map(|(_, Item { value, .. })| value)
                .sum::<u32>()
        })
        .max()
        .unwrap()
}
