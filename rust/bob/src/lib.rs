pub fn reply(message: &str) -> &str {
    if message.chars().all(|c| c.is_ascii_whitespace()) {
        return "Fine. Be that way!";
    }

    let is_question = message.trim_end().ends_with('?');

    if message.chars().any(|c| c.is_ascii_alphabetic()) && message.to_uppercase() == message {
        return if is_question {
            "Calm down, I know what I'm doing!"
        } else {
            "Whoa, chill out!"
        };
    }

    if is_question {
        return "Sure.";
    }

    "Whatever."
}
