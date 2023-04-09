pub struct RailFence {
    rails: usize,
}

impl RailFence {
    pub fn new(rails: u32) -> Self {
        Self {
            rails: rails as usize,
        }
    }

    pub fn encode(&self, text: &str) -> String {
        self.build_pattern(text.chars().count())
            .iter()
            .map(|line| {
                line.iter()
                    .map(|&c| text.chars().nth(c).unwrap())
                    .collect::<String>()
            })
            .collect()
    }

    pub fn decode(&self, cipher: &str) -> String {
        let cipher_chars: Vec<_> = cipher.chars().collect();
        let mut result = vec![char::default(); cipher_chars.len()];
        let mut index = 0;
        for line in self.build_pattern(cipher_chars.len()) {
            for c in line {
                result[c] = cipher_chars[index];
                index += 1;
            }
        }

        result.iter().collect()
    }

    fn build_pattern(&self, message_length: usize) -> Vec<Vec<usize>> {
        let mut pattern = vec![Vec::new(); self.rails];
        let mut r = 0i32;
        let mut r_offset = 1i32;
        for c in 0..message_length {
            pattern[r as usize].push(c);

            r += r_offset;
            if !(r >= 0 && r < pattern.len() as i32) {
                r_offset *= -1;
                r += r_offset * 2;
            }
        }

        pattern
    }
}
