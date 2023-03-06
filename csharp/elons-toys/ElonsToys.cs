using System;

class RemoteControlCar
{
    const int MAX_DRIVE_COUNT = 100;

    int driveCount;

    public static RemoteControlCar Buy() => new RemoteControlCar();

    public string DistanceDisplay() => $"Driven {driveCount * 20} meters";

    public string BatteryDisplay() =>
        (driveCount == MAX_DRIVE_COUNT)
            ? "Battery empty"
            : $"Battery at {MAX_DRIVE_COUNT - driveCount}%";

    public void Drive()
    {
        if (driveCount != MAX_DRIVE_COUNT)
        {
            ++driveCount;
        }
    }
}
