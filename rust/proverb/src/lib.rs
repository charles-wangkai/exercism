pub fn build_proverb(list: &[&str]) -> String {
    (0..list.len())
        .map(|i| {
            if i == list.len() - 1 {
                format!("And all for the want of a {}.", list[0])
            } else {
                format!("For want of a {} the {} was lost.", list[i], list[i + 1])
            }
        })
        .collect::<Vec<_>>()
        .join("\n")
}
