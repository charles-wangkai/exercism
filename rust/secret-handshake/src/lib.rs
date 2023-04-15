const SIGNALS: &[&str] = &["wink", "double blink", "close your eyes", "jump"];

pub fn actions(n: u8) -> Vec<&'static str> {
    let mut result = Vec::new();
    let mut rest = n;
    for &signal in SIGNALS {
        if (rest & 1) == 1 {
            result.push(signal);
        }
        rest >>= 1;
    }
    if n >= 16 {
        result.reverse()
    }

    result
}
