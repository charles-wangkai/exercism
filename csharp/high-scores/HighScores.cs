using System;
using System.Collections.Generic;
using System.Linq;

public class HighScores
{
    List<int> scores;

    public HighScores(List<int> scores)
    {
        this.scores = scores;
    }

    public List<int> Scores() => scores;

    public int Latest() => scores.Last();

    public int PersonalBest() => scores.Max();

    public List<int> PersonalTopThree() => scores.OrderByDescending(x => x).Take(3).ToList();
}
