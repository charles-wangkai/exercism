class Lasagna
{
    public int ExpectedMinutesInOven()
    {
        return 40;
    }

    public int RemainingMinutesInOven(int alreadyMinutes)
    {
        return ExpectedMinutesInOven() - alreadyMinutes;
    }

    public int PreparationTimeInMinutes(int layerNum)
    {
        return layerNum * 2;
    }

    public int ElapsedTimeInMinutes(int layerNum, int alreadyMinutes)
    {
        return PreparationTimeInMinutes(layerNum) + alreadyMinutes;
    }
}
