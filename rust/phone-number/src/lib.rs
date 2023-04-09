pub fn number(user_number: &str) -> Option<String> {
    if user_number
        .chars()
        .any(|c| !c.is_ascii_digit() && !" ()-.+".contains(c))
    {
        return None;
    }

    let mut digits = user_number
        .chars()
        .filter(|c| c.is_ascii_digit())
        .collect::<Vec<_>>();
    if digits.len() == 11 {
        if digits[0] != '1' {
            return None;
        }

        digits = digits[1..].to_vec();
    }
    if digits.len() != 10
        || !(digits[0] >= '2' && digits[0] <= '9' && digits[3] >= '2' && digits[3] <= '9')
    {
        return None;
    }

    Some(digits.iter().collect())
}
