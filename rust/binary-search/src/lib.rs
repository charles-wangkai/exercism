use std::cmp::Ordering;

pub fn find<T: AsRef<[U]>, U: Ord>(array: T, key: U) -> Option<usize> {
    let mut lower_index = 0;
    let mut upper_index = array.as_ref().len() as i32 - 1;
    while lower_index <= upper_index {
        let middle_index = (lower_index + upper_index) / 2;
        match array.as_ref()[middle_index as usize].cmp(&key) {
            Ordering::Less => lower_index = middle_index + 1,
            Ordering::Greater => upper_index = middle_index - 1,
            Ordering::Equal => return Some(middle_index as usize),
        }
    }

    None
}
