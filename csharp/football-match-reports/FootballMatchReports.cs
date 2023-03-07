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
        if (report is int supporterNum)
        {
            return $"There are {supporterNum} supporters at the match.";
        }
        if (report is string announcement)
        {
            return announcement;
        }
        if (report is Injury injury)
        {
            return $"Oh no! {injury.GetDescription()} Medics are on the field.";
        }
        if (report is Incident incident)
        {
            return incident.GetDescription();
        }
        if (report is Manager manager)
        {
            return manager.Name + ((manager.Club == null) ? "" : $" ({manager.Club})");
        }

        throw new ArgumentException();
    }
}
