use std::{collections::HashMap, iter};

use itertools::Itertools;

pub fn tally(match_results: &str) -> String {
    let mut name_to_team = HashMap::new();
    for match_result in match_results.split('\n') {
        if let [name1, name2, outcome] = match_result.split(';').collect::<Vec<_>>()[..] {
            if !name_to_team.contains_key(name1) {
                name_to_team.insert(name1, Team::new(name1));
            }
            if !name_to_team.contains_key(name2) {
                name_to_team.insert(name2, Team::new(name2));
            }

            if outcome == "win" {
                name_to_team.get_mut(name1).unwrap().win_count += 1;
                name_to_team.get_mut(name2).unwrap().loss_count += 1;
            } else if outcome == "draw" {
                name_to_team.get_mut(name1).unwrap().draw_count += 1;
                name_to_team.get_mut(name2).unwrap().draw_count += 1;
            } else if outcome == "loss" {
                name_to_team.get_mut(name1).unwrap().loss_count += 1;
                name_to_team.get_mut(name2).unwrap().win_count += 1;
            }
        }
    }

    iter::once(String::from(
        "Team                           | MP |  W |  D |  L |  P",
    ))
    .chain(
        name_to_team
            .values()
            .sorted_unstable_by_key(|team| (-(team.get_points() as i16), &team.name))
            .map(|team| {
                format!(
                    "{:<30} | {:>2} | {:>2} | {:>2} | {:>2} | {:>2}",
                    team.name,
                    team.win_count + team.draw_count + team.loss_count,
                    team.win_count,
                    team.draw_count,
                    team.loss_count,
                    team.get_points()
                )
            }),
    )
    .collect::<Vec<_>>()
    .join("\n")
}

struct Team {
    name: String,
    win_count: u8,
    draw_count: u8,
    loss_count: u8,
}

impl Team {
    fn new(name: &str) -> Self {
        Self {
            name: String::from(name),
            win_count: 0,
            draw_count: 0,
            loss_count: 0,
        }
    }

    fn get_points(&self) -> u8 {
        self.win_count * 3 + self.draw_count
    }
}
