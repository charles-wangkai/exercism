using System;

class RemoteControlCar
{
    int speed;
    int batteryDrain;
    int driveCount;

    public RemoteControlCar(int speed, int batteryDrain)
    {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
    }

    public bool BatteryDrained() => batteryDrain * (driveCount + 1) > 100;

    public int DistanceDriven() => speed * driveCount;

    public void Drive()
    {
        if (!BatteryDrained())
        {
            ++driveCount;
        }
    }

    public static RemoteControlCar Nitro() => new RemoteControlCar(50, 4);

    public int GetMaxDistance() => 100 / batteryDrain * speed;
}

class RaceTrack
{
    int distance;

    public RaceTrack(int distance)
    {
        this.distance = distance;
    }

    public bool TryFinishTrack(RemoteControlCar car) => car.GetMaxDistance() >= distance;
}
