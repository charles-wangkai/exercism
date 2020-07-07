using System;

public static class Gigasecond
{
    public static DateTime Add(DateTime moment)
    {
        return moment + new TimeSpan(0, 0, 1_000_000_000);
    }
}