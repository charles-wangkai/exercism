use lazy_static::lazy_static;
use std::collections::HashMap;

#[derive(Debug, PartialEq, Eq)]
pub struct Dna {
    dna: String,
}

#[derive(Debug, PartialEq, Eq)]
pub struct Rna {
    rna: String,
}

lazy_static! {
    static ref DNA_TO_RNA: HashMap<char, char> =
        HashMap::from([('A', 'U'), ('C', 'G'), ('G', 'C'), ('T', 'A')]);
}

impl Dna {
    pub fn new(dna: &str) -> Result<Self, usize> {
        match dna.chars().enumerate().find(|(_, c)| !"ACGT".contains(*c)) {
            Some((i, _)) => Err(i),
            None => Ok(Self {
                dna: String::from(dna),
            }),
        }
    }

    pub fn into_rna(self) -> Rna {
        Rna::new(&self.dna.chars().map(|c| DNA_TO_RNA[&c]).collect::<String>()).unwrap()
    }
}

impl Rna {
    pub fn new(rna: &str) -> Result<Self, usize> {
        match rna.chars().enumerate().find(|(_, c)| !"ACGU".contains(*c)) {
            Some((i, _)) => Err(i),
            None => Ok(Self {
                rna: String::from(rna),
            }),
        }
    }
}
