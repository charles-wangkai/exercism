def tally(tournament_results):
    name_to_team = {}

    for result in tournament_results:
        name1, name2, outcome = result.split(';')

        if name1 not in name_to_team:
            name_to_team[name1] = Team(name1)
        if name2 not in name_to_team:
            name_to_team[name2] = Team(name2)

        if outcome == 'win':
            name_to_team[name1].win_count += 1
            name_to_team[name2].loss_count += 1
        elif outcome == 'draw':
            name_to_team[name1].draw_count += 1
            name_to_team[name2].draw_count += 1
        elif outcome == 'loss':
            name_to_team[name1].loss_count += 1
            name_to_team[name2].win_count += 1

    return build_table(name_to_team)


def build_table(name_to_team):
    return ['Team                           | MP |  W |  D |  L |  P'] + \
        [f'{team.name:<30} | {team.matches_played:>2} | {team.win_count:>2} | {team.draw_count:>2} | {team.loss_count:>2} | {team.point:>2}'
         for team in build_sorted_teams(name_to_team)]


def build_sorted_teams(name_to_team):
    return sorted(name_to_team.values(), key=lambda team: (-team.point, team.name))


class Team:
    def __init__(self, name):
        self.name = name
        self.win_count = self.draw_count = self.loss_count = 0

    @property
    def matches_played(self):
        return self.win_count + self.draw_count + self.loss_count

    @property
    def point(self):
        return self.win_count * 3 + self.draw_count
