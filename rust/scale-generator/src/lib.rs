use std::collections::{HashMap, HashSet};

use lazy_static::lazy_static;

// You should change this.
//
// Depending on your implementation, there are a variety of potential errors
// which might occur. They aren't checked by the test suite in order to
// allow the greatest freedom of implementation, but real libraries should
// provide useful, descriptive errors so that downstream code can react
// appropriately.
//
// One common idiom is to define an Error enum which wraps all potential
// errors. Another common idiom is to use a helper type such as failure::Error
// which does more or less the same thing but automatically.
#[derive(Debug)]
pub struct Error;

pub struct Scale {
    tonic: String,
    intervals: Option<String>,
}

const SHARPS: &[&str] = &[
    "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#",
];
const FLATS: &[&str] = &[
    "A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab",
];
lazy_static! {
    static ref FLAT_TONICS: HashSet<&'static str> =
        HashSet::from(["F", "Bb", "Eb", "Ab", "Db", "Gb", "d", "g", "c", "f", "bb", "eb"]);
    static ref INTERVAL_TO_OFFSET: HashMap<char, usize> =
        HashMap::from([('m', 1), ('M', 2), ('A', 3)]);
}

impl Scale {
    pub fn new(tonic: &str, intervals: &str) -> Result<Self, Error> {
        Ok(Self {
            tonic: String::from(tonic),
            intervals: Some(String::from(intervals)),
        })
    }

    pub fn chromatic(tonic: &str) -> Result<Self, Error> {
        Ok(Self {
            tonic: String::from(tonic),
            intervals: None,
        })
    }

    pub fn enumerate(&self) -> Vec<String> {
        let scale = if FLAT_TONICS.contains(&*self.tonic) {
            FLATS
        } else {
            SHARPS
        };
        let start_index = scale
            .iter()
            .position(|&x| x.to_lowercase() == self.tonic.to_lowercase())
            .unwrap();

        match &self.intervals {
            Some(intervals) => {
                let mut result = Vec::new();
                let mut index = start_index;
                for interval in intervals.chars() {
                    result.push(String::from(scale[index]));
                    index = (index + INTERVAL_TO_OFFSET[&interval]) % scale.len();
                }
                result.push(String::from(scale[start_index]));

                result
            }
            None => [&scale[start_index..], &scale[..=start_index]]
                .concat()
                .iter()
                .map(|&x| String::from(x))
                .collect(),
        }
    }
}
