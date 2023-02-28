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

    public bool BatteryDrained()
    {
        return batteryDrain * (driveCount + 1) > 100;
    }

    public int DistanceDriven()
    {
        return speed * driveCount;
    }

    public void Drive()
    {
        if (!BatteryDrained())
        {
            ++driveCount;
        }
    }

    public static RemoteControlCar Nitro()
    {
        return new RemoteControlCar(50, 4);
    }

    public int GetMaxDistance()
    {
        return 100 / batteryDrain * speed;
    }
}

class RaceTrack
{
    int distance;

    public RaceTrack(int distance)
    {
        this.distance = distance;
    }

    public bool TryFinishTrack(RemoteControlCar car)
    {
        return car.GetMaxDistance() >= distance;
    }
}
