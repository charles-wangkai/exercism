using System;
using System.Collections.Generic;

public static class PlayAnalyzer
{
    public static string AnalyzeOnField(int shirtNum)
    {
        if (shirtNum == 1)
        {
            return "goalie";
        }
        if (shirtNum == 2)
        {
            return "left back";
        }
        if (shirtNum == 3 || shirtNum == 4)
        {
            return "center back";
        }
        if (shirtNum == 5)
        {
            return "right back";
        }
        if (shirtNum >= 6 && shirtNum <= 8)
        {
            return "midfielder";
        }
        if (shirtNum == 9)
        {
            return "left wing";
        }
        if (shirtNum == 10)
        {
            return "striker";
        }
        if (shirtNum == 11)
        {
            return "right wing";
        }

        throw new ArgumentOutOfRangeException();
    }

    public static string AnalyzeOffField(object report)
    {
        if (report is int)
        {
            return $"There are {report} supporters at the match.";
        }
        if (report is string)
        {
            return (string)report;
        }
        if (report is Injury)
        {
            return $"Oh no! {((Incident)report).GetDescription()} Medics are on the field.";
        }
        if (report is Incident)
        {
            return ((Incident)report).GetDescription();
        }
        if (report is Manager)
        {
            Manager manager = (Manager)report;

            return manager.Name + ((manager.Club == null) ? "" : $" ({manager.Club})");
        }

        throw new ArgumentException();
    }
}
