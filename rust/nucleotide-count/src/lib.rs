use std::collections::HashMap;

const SYMBOLS: &str = "ACGT";

pub fn count(nucleotide: char, dna: &str) -> Result<usize, char> {
    if !SYMBOLS.contains(nucleotide) {
        return Err(nucleotide);
    }

    let mut result = 0;
    for c in dna.chars() {
        if !SYMBOLS.contains(c) {
            return Err(c);
        }

        if c == nucleotide {
            result += 1;
        }
    }

    Ok(result)
}

pub fn nucleotide_counts(dna: &str) -> Result<HashMap<char, usize>, char> {
    let mut result = HashMap::new();
    for symbol in SYMBOLS.chars() {
        match count(symbol, dna) {
            Ok(count) => {
                result.insert(symbol, count);
            }
            Err(c) => return Err(c),
        }
    }

    Ok(result)
}
