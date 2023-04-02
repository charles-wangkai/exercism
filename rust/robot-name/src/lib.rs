use std::{collections::HashSet, sync::Mutex};

use lazy_static::lazy_static;
use rand::Rng;

pub struct Robot {
    name: String,
}

lazy_static! {
    static ref SEEN: Mutex<HashSet<String>> = Mutex::new(HashSet::new());
}

fn generate_name() -> String {
    loop {
        let result = format!(
            "{}{}{}{}{}",
            generate_letter(),
            generate_letter(),
            generate_digit(),
            generate_digit(),
            generate_digit()
        );

        let mut seen = SEEN.lock().unwrap();
        if !seen.contains(&result) {
            seen.insert(result.clone());

            return result;
        }
    }
}

fn generate_letter() -> char {
    rand::thread_rng().gen_range('A'..='Z')
}

fn generate_digit() -> u8 {
    rand::thread_rng().gen_range(0..=9)
}

impl Robot {
    pub fn new() -> Self {
        Self {
            name: generate_name(),
        }
    }

    pub fn name(&self) -> &str {
        &self.name
    }

    pub fn reset_name(&mut self) {
        self.name = generate_name();
    }
}

impl Default for Robot {
    fn default() -> Self {
        Self::new()
    }
}
