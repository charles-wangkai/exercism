use std::collections::HashMap;

pub struct CodonsInfo<'a> {
    coden_to_protein: HashMap<&'a str, &'a str>,
}

impl CodonsInfo<'_> {
    pub fn name_for(&self, codon: &str) -> Option<&str> {
        self.coden_to_protein.get(codon).copied()
    }

    pub fn of_rna(&self, rna: &str) -> Option<Vec<&str>> {
        const CODON_LENGTH: usize = 3;

        let mut result = Vec::new();
        for begin_index in (0..rna.len()).step_by(3) {
            let coden = &rna[begin_index..(begin_index + CODON_LENGTH).min(rna.len())];
            if coden.len() != CODON_LENGTH || !self.coden_to_protein.contains_key(coden) {
                return None;
            }
            if self.coden_to_protein[coden] == "stop codon" {
                break;
            }

            result.push(self.coden_to_protein[coden]);
        }

        Some(result)
    }
}

pub fn parse<'a>(pairs: Vec<(&'a str, &'a str)>) -> CodonsInfo<'a> {
    let mut coden_to_protein = HashMap::new();
    for (coden, protein) in pairs {
        coden_to_protein.insert(coden, protein);
    }

    CodonsInfo { coden_to_protein }
}
