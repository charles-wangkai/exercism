/// What should the type of _function be?
pub fn map<T, U, F: FnMut(T) -> U>(input: Vec<T>, mut function: F) -> Vec<U> {
    let mut result = Vec::new();
    for x in input {
        result.push(function(x));
    }

    result
}
