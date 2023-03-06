class Lasagna
{
    public int ExpectedMinutesInOven() => 40;

    public int RemainingMinutesInOven(int alreadyMinutes) =>
        ExpectedMinutesInOven() - alreadyMinutes;

    public int PreparationTimeInMinutes(int layerNum) => layerNum * 2;

    public int ElapsedTimeInMinutes(int layerNum, int alreadyMinutes) =>
        PreparationTimeInMinutes(layerNum) + alreadyMinutes;
}
