using System;
using System.Linq;

class BirdCount
{
    private int[] birdsPerDay;

    public BirdCount(int[] birdsPerDay)
    {
        this.birdsPerDay = birdsPerDay;
    }

    public static int[] LastWeek() => new[] { 0, 2, 5, 3, 7, 8, 4 };

    public int Today() => birdsPerDay.Last();

    public void IncrementTodaysCount()
    {
        ++birdsPerDay[birdsPerDay.Length - 1];
    }

    public bool HasDayWithoutBirds() => birdsPerDay.Any(x => x == 0);

    public int CountForFirstDays(int numberOfDays) =>
        Enumerable.Range(0, numberOfDays).Select(i => birdsPerDay[i]).Sum();

    public int BusyDays() => birdsPerDay.Where(x => x >= 5).Count();
}
