def tally(tournament_results):
    name2team = {}

    for result in tournament_results.splitlines():
        name1, name2, outcome = result.split(';')

        if name1 not in name2team:
            name2team[name1] = Team(name1)
        if name2 not in name2team:
            name2team[name2] = Team(name2)

        if outcome == 'win':
            name2team[name1].w += 1
            name2team[name2].l += 1
        elif outcome == 'draw':
            name2team[name1].d += 1
            name2team[name2].d += 1
        elif outcome == 'loss':
            name2team[name1].l += 1
            name2team[name2].w += 1

    return '\n'.join(['Team                           | MP |  W |  D |  L |  P']
                     + ['{:<30} | {:>2} | {:>2} | {:>2} | {:>2} | {:>2}'.format(team.name, team.get_mp(), team.w, team.d, team.l, team.get_p())
                        for team in sorted(name2team.values(), key=lambda t: (-t.get_p(), t.name))])


class Team:
    def __init__(self, name):
        self.name = name
        self.w = self.d = self.l = 0

    def get_mp(self):
        return self.w + self.d + self.l

    def get_p(self):
        return self.w * 3 + self.d
