const CHUNK_NAMES: &[&str] = &[
    "",
    "thousand",
    "million",
    "billion",
    "trillion",
    "quadrillion",
    "quintillion",
];
const ONE_NAMES: &[&str] = &[
    "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
];
const TEN_NAMES: &[&str] = &[
    "", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety",
];
const TEEN_NAMES: &[&str] = &[
    "eleven",
    "twelve",
    "thirteen",
    "fourteen",
    "fifteen",
    "sixteen",
    "seventeen",
    "eighteen",
    "nineteen",
];

pub fn encode(n: u64) -> String {
    if n == 0 {
        return String::from("zero");
    }

    let s = n.to_string();

    (0..s.len())
        .step_by(3)
        .map(|begin_index| {
            reverse_string(&reverse_string(&s)[begin_index..(begin_index + 3).min(s.len())])
                .parse::<u32>()
                .unwrap()
        })
        .zip(CHUNK_NAMES)
        .filter(|(value, _)| *value != 0)
        .map(|(value, &chunk_name)| {
            format!(
                "{}{}{}",
                say_within_thousand(value),
                if chunk_name.is_empty() { "" } else { " " },
                chunk_name
            )
        })
        .rev()
        .collect::<Vec<_>>()
        .join(" ")
}

fn say_within_thousand(n: u32) -> String {
    let mut parts = Vec::new();

    if n >= 100 {
        parts.push(format!("{} hundred", ONE_NAMES[(n / 100) as usize]));
    }

    let rest = n % 100;
    if rest != 0 {
        if (11..=19).contains(&rest) {
            parts.push(String::from(TEEN_NAMES[(rest - 11) as usize]));
        } else {
            let mut part = String::new();

            let ten = rest / 10;
            if ten != 0 {
                part += TEN_NAMES[ten as usize];
            }

            let one = rest % 10;
            if one != 0 {
                if !part.is_empty() {
                    part += "-";
                }
                part += ONE_NAMES[one as usize];
            }

            if !part.is_empty() {
                parts.push(part);
            }
        }
    }

    parts.join(" ")
}

fn reverse_string(s: &str) -> String {
    s.chars().rev().collect()
}
