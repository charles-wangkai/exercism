using System;

class RemoteControlCar
{
    const int MAX_DRIVE_COUNT = 100;

    int driveCount;

    public static RemoteControlCar Buy()
    {
        return new RemoteControlCar();
    }

    public string DistanceDisplay()
    {
        return $"Driven {driveCount * 20} meters";
    }

    public string BatteryDisplay()
    {
        return (driveCount == MAX_DRIVE_COUNT)
            ? "Battery empty"
            : $"Battery at {MAX_DRIVE_COUNT - driveCount}%";
    }

    public void Drive()
    {
        if (driveCount != MAX_DRIVE_COUNT)
        {
            ++driveCount;
        }
    }
}
