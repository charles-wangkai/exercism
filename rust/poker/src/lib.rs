use std::str;

use itertools::Itertools;

/// Given a list of poker hands, return a list of those hands which win.
///
/// Note the type signature: this function should return _the same_ reference to
/// the winning hand(s) as were passed in, not reconstructed strings which happen to be equal.
pub fn winning_hands<'a>(hands: &[&'a str]) -> Vec<&'a str> {
    let hands: Vec<_> = hands.iter().map(|&s| Hand::from(s)).collect();
    let best_category_ranks = hands
        .iter()
        .map(|hand| (hand.category, &hand.ranks))
        .max()
        .unwrap();

    hands
        .iter()
        .filter(|hand| (hand.category, &hand.ranks) == best_category_ranks)
        .map(|hand| hand.s)
        .collect()
}

struct Hand<'a> {
    s: &'a str,
    category: i8,
    ranks: Vec<u8>,
}

impl<'a> Hand<'a> {
    fn from(s: &'a str) -> Self {
        let cards: [Card; 5] = s
            .split_ascii_whitespace()
            .map(Card::from)
            .sorted_unstable_by_key(|card| -(card.value as i16))
            .collect::<Vec<_>>()
            .try_into()
            .unwrap();

        let (category, ranks) = if is_straight_flush(&cards) {
            (
                -1,
                if is_ace_to_five(&cards) {
                    vec![5]
                } else {
                    vec![cards[0].value]
                },
            )
        } else if is_square(&cards) {
            (
                -2,
                if cards[0].value == cards[3].value {
                    vec![cards[0].value, cards[4].value]
                } else {
                    vec![cards[4].value, cards[0].value]
                },
            )
        } else if is_full(&cards) {
            (
                -3,
                if cards[0].value == cards[2].value && cards[3].value == cards[4].value {
                    vec![cards[0].value, cards[3].value]
                } else {
                    vec![cards[2].value, cards[0].value]
                },
            )
        } else if is_flush(&cards) {
            (
                -4,
                vec![
                    cards[0].value,
                    cards[1].value,
                    cards[2].value,
                    cards[3].value,
                    cards[4].value,
                ],
            )
        } else if is_straight(&cards) {
            (
                -5,
                if is_ace_to_five(&cards) {
                    vec![5]
                } else {
                    vec![cards[0].value]
                },
            )
        } else if is_three(&cards) {
            (
                -6,
                if cards[0].value == cards[2].value {
                    vec![cards[0].value, cards[3].value, cards[4].value]
                } else if cards[1].value == cards[3].value {
                    vec![cards[1].value, cards[0].value, cards[4].value]
                } else {
                    vec![cards[2].value, cards[0].value, cards[1].value]
                },
            )
        } else if is_double_pair(&cards) {
            (
                -7,
                if cards[0].value == cards[1].value && cards[2].value == cards[3].value {
                    vec![cards[0].value, cards[2].value, cards[4].value]
                } else if cards[0].value == cards[1].value && cards[3].value == cards[4].value {
                    vec![cards[0].value, cards[3].value, cards[2].value]
                } else {
                    vec![cards[1].value, cards[3].value, cards[0].value]
                },
            )
        } else if is_one_pair(&cards) {
            (
                -8,
                if cards[0].value == cards[1].value {
                    vec![
                        cards[0].value,
                        cards[2].value,
                        cards[3].value,
                        cards[4].value,
                    ]
                } else if cards[1].value == cards[2].value {
                    vec![
                        cards[1].value,
                        cards[0].value,
                        cards[3].value,
                        cards[4].value,
                    ]
                } else if cards[2].value == cards[3].value {
                    vec![
                        cards[2].value,
                        cards[0].value,
                        cards[1].value,
                        cards[4].value,
                    ]
                } else {
                    vec![
                        cards[3].value,
                        cards[0].value,
                        cards[1].value,
                        cards[2].value,
                    ]
                },
            )
        } else {
            (
                -9,
                vec![
                    cards[0].value,
                    cards[1].value,
                    cards[2].value,
                    cards[3].value,
                    cards[4].value,
                ],
            )
        };

        Self { s, category, ranks }
    }
}

fn is_one_pair(cards: &[Card; 5]) -> bool {
    (0..4).any(|i| cards[i].value == cards[i + 1].value)
}

fn is_double_pair(cards: &[Card; 5]) -> bool {
    (cards[0].value == cards[1].value
        && (cards[2].value == cards[3].value || cards[3].value == cards[4].value))
        || (cards[1].value == cards[2].value && cards[3].value == cards[4].value)
}

fn is_three(cards: &[Card; 5]) -> bool {
    cards[0].value == cards[2].value
        || cards[1].value == cards[3].value
        || cards[2].value == cards[4].value
}

fn is_straight(cards: &[Card; 5]) -> bool {
    (0..4).all(|i| cards[i].value == cards[i + 1].value + 1) || is_ace_to_five(cards)
}

fn is_flush(cards: &[Card; 5]) -> bool {
    cards.iter().map(|card| card.suit).unique().count() == 1
}

fn is_full(cards: &[Card; 5]) -> bool {
    (cards[0].value == cards[2].value && cards[3].value == cards[4].value)
        || (cards[0].value == cards[1].value && cards[2].value == cards[4].value)
}

fn is_square(cards: &[Card; 5]) -> bool {
    cards[0].value == cards[3].value || cards[1].value == cards[4].value
}

fn is_ace_to_five(cards: &[Card; 5]) -> bool {
    cards[0].value == 14
        && cards[1].value == 5
        && cards[2].value == 4
        && cards[3].value == 3
        && cards[4].value == 2
}

fn is_straight_flush(cards: &[Card; 5]) -> bool {
    is_straight(cards) && is_flush(cards)
}

#[derive(Debug)]
struct Card {
    value: u8,
    suit: char,
}

impl Card {
    fn from(s: &str) -> Self {
        let s = s.as_bytes();

        Self {
            value: match str::from_utf8(&s[..s.len() - 1]).unwrap() {
                "A" => 14,
                "K" => 13,
                "Q" => 12,
                "J" => 11,
                x => x.parse().unwrap(),
            },
            suit: s[s.len() - 1] as char,
        }
    }
}
