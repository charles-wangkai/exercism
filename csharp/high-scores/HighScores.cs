using System;
using System.Collections.Generic;
using System.Linq;

public class HighScores
{
    IList<int> scores;

    public HighScores(List<int> scores)
    {
        this.scores = scores;
    }

    public IList<int> Scores() => scores;

    public int Latest() => scores.Last();

    public int PersonalBest() => scores.Max();

    public IList<int> PersonalTopThree() => scores.OrderByDescending(x => x).Take(3).ToList();
}
