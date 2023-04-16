pub enum Category {
    Ones,
    Twos,
    Threes,
    Fours,
    Fives,
    Sixes,
    FullHouse,
    FourOfAKind,
    LittleStraight,
    BigStraight,
    Choice,
    Yacht,
}

type Dice = [u8; 5];
pub fn score(mut dice: Dice, category: Category) -> u8 {
    dice.sort_unstable();

    match category {
        Category::Ones => dice.iter().filter(|&&x| x == 1).sum(),
        Category::Twos => dice.iter().filter(|&&x| x == 2).sum(),
        Category::Threes => dice.iter().filter(|&&x| x == 3).sum(),
        Category::Fours => dice.iter().filter(|&&x| x == 4).sum(),
        Category::Fives => dice.iter().filter(|&&x| x == 5).sum(),
        Category::Sixes => dice.iter().filter(|&&x| x == 6).sum(),
        Category::FullHouse => {
            if dice[0] == dice[1]
                && dice[3] == dice[4]
                && dice[0] != dice[4]
                && (dice[2] == dice[1] || dice[2] == dice[3])
            {
                dice.iter().sum()
            } else {
                0
            }
        }
        Category::FourOfAKind => {
            if dice[0] == dice[3] || dice[1] == dice[4] {
                dice[1] * 4
            } else {
                0
            }
        }
        Category::LittleStraight => {
            if (1..=5).all(|x| dice.contains(&x)) {
                30
            } else {
                0
            }
        }
        Category::BigStraight => {
            if (2..=6).all(|x| dice.contains(&x)) {
                30
            } else {
                0
            }
        }
        Category::Choice => dice.iter().sum(),
        Category::Yacht => {
            if dice[0] == dice[4] {
                50
            } else {
                0
            }
        }
    }
}
