#[derive(Debug, PartialEq, Eq)]
pub enum Comparison {
    Equal,
    Sublist,
    Superlist,
    Unequal,
}

pub fn sublist<T: PartialEq>(first_list: &[T], second_list: &[T]) -> Comparison {
    let sublist = is_sublist(first_list, second_list);
    let superlist = is_sublist(second_list, first_list);

    if sublist && superlist {
        Comparison::Equal
    } else if sublist {
        Comparison::Sublist
    } else if superlist {
        Comparison::Superlist
    } else {
        Comparison::Unequal
    }
}

fn is_sublist<T: PartialEq>(list1: &[T], list2: &[T]) -> bool {
    list1.len() <= list2.len()
        && (0..=(list2.len() - list1.len()))
            .any(|begin_index| (0..list1.len()).all(|i| list1[i] == list2[begin_index + i]))
}
