using System;
using System.Collections.Generic;
using System.Linq;

public class HighScores
{
    List<int> _scores;

    public HighScores(List<int> scores)
    {
        _scores = scores;
    }

    public List<int> Scores()
    {
        return _scores;
    }

    public int Latest()
    {
        return _scores[_scores.Count - 1];
    }

    public int PersonalBest()
    {
        return _scores.Max();
    }

    public List<int> PersonalTopThree()
    {
        return _scores.OrderByDescending(score => score).Take(3).ToList();
    }
}