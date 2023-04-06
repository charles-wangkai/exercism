pub fn answer(command: &str) -> Option<i32> {
    let simplified = command
        .replace("What is", "")
        .replace("by", "")
        .replace('?', "")
        .replace("to the", "")
        .replace("th", "")
        .replace("nd", "")
        .replace("power", "");
    let parts: Vec<_> = simplified.split_ascii_whitespace().collect();
    if parts.len() % 2 == 0 {
        return None;
    }

    let mut result = parts[0].parse().ok()?;
    for i in (1..parts.len()).step_by(2) {
        result = compute(result, parts[i], parts[i + 1])?;
    }

    Some(result)
}

fn compute(x: i32, operation: &str, y: &str) -> Option<i32> {
    let y: i32 = y.parse().ok()?;

    match operation {
        "plus" => Some(x + y),
        "minus" => Some(x - y),
        "multiplied" => Some(x * y),
        "divided" => Some(x / y),
        "raised" => Some(x.pow(y as u32)),
        _ => None,
    }
}
