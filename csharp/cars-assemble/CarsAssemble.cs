using System;

static class AssemblyLine
{
    public static double SuccessRate(int speed)
    {
        if (speed == 0)
        {
            return 0;
        }
        if (speed <= 4)
        {
            return 1;
        }
        if (speed <= 8)
        {
            return 0.9;
        }
        if (speed == 9)
        {
            return 0.8;
        }

        return 0.77;
    }

    public static double ProductionRatePerHour(int speed)
    {
        return 221 * speed * SuccessRate(speed);
    }

    public static int WorkingItemsPerMinute(int speed)
    {
        return (int)Math.Floor(ProductionRatePerHour(speed) / 60);
    }
}
