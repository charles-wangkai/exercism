use std::{collections::HashSet, fs};

use anyhow::Error;

/// While using `&[&str]` to handle flags is convenient for exercise purposes,
/// and resembles the output of [`std::env::args`], in real-world projects it is
/// both more convenient and more idiomatic to contain runtime configuration in
/// a dedicated struct. Therefore, we suggest that you do so in this exercise.
///
/// In the real world, it's common to use crates such as [`clap`] or
/// [`structopt`] to handle argument parsing, and of course doing so is
/// permitted in this exercise as well, though it may be somewhat overkill.
///
/// [`clap`]: https://crates.io/crates/clap
/// [`std::env::args`]: https://doc.rust-lang.org/std/env/fn.args.html
/// [`structopt`]: https://crates.io/crates/structopt
#[derive(Debug)]
pub struct Flags {
    flags: HashSet<String>,
}

impl Flags {
    pub fn new(flags: &[&str]) -> Self {
        Self {
            flags: flags.iter().map(|&flag| String::from(flag)).collect(),
        }
    }
}

pub fn grep(pattern: &str, flags: &Flags, files: &[&str]) -> Result<Vec<String>, Error> {
    let mut result = Vec::new();
    for &file in files {
        let mut found = false;
        for (i, line) in fs::read_to_string(file)?.lines().enumerate() {
            if check(pattern, flags, line) {
                if flags.flags.contains("-l") {
                    if !found {
                        result.push(String::from(file));
                    }
                } else {
                    result.push(format!(
                        "{}{}{}",
                        if files.len() > 1 {
                            format!("{file}:")
                        } else {
                            String::new()
                        },
                        if flags.flags.contains("-n") {
                            format!("{}:", i + 1)
                        } else {
                            String::new()
                        },
                        line
                    ));
                }

                found = true;
            }
        }
    }

    Ok(result)
}

fn check(pattern: &str, flags: &Flags, line: &str) -> bool {
    let matched = !(flags.flags.contains("-x") && line.len() != pattern.len())
        && if flags.flags.contains("-i") {
            line.to_lowercase().contains(&pattern.to_lowercase())
        } else {
            line.contains(pattern)
        };

    if flags.flags.contains("-v") {
        !matched
    } else {
        matched
    }
}
