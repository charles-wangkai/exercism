use enum_iterator::Sequence;
use int_enum::IntEnum;

pub struct Allergies {
    score: u32,
}

#[repr(u32)]
#[derive(Clone, Copy, Debug, PartialEq, Eq, IntEnum, Sequence)]
pub enum Allergen {
    Eggs = 1,
    Peanuts = 2,
    Shellfish = 4,
    Strawberries = 8,
    Tomatoes = 16,
    Chocolate = 32,
    Pollen = 64,
    Cats = 128,
}

impl Allergies {
    pub fn new(score: u32) -> Self {
        Self { score }
    }

    pub fn is_allergic_to(&self, allergen: &Allergen) -> bool {
        (self.score & allergen.int_value()) != 0
    }

    pub fn allergies(&self) -> Vec<Allergen> {
        enum_iterator::all()
            .filter(|allergen| self.is_allergic_to(allergen))
            .collect()
    }
}
