pub fn brackets_are_balanced(string: &str) -> bool {
    let mut stack = Vec::new();
    for c in string.chars() {
        if "()[]{}".contains(c) {
            if "([{".contains(c) {
                stack.push(c);
            } else if stack.is_empty()
                || (c == ')' && stack.last().copied().unwrap() != '(')
                || (c == ']' && stack.last().copied().unwrap() != '[')
                || (c == '}' && stack.last().copied().unwrap() != '{')
            {
                return false;
            } else {
                stack.pop();
            }
        }
    }

    stack.is_empty()
}
