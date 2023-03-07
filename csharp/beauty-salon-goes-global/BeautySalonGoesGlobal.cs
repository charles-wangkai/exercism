using System;
using System.Collections.Generic;
using System.Globalization;

public enum Location
{
    NewYork,
    London,
    Paris
}

public enum AlertLevel
{
    Early,
    Standard,
    Late
}

public static class Appointment
{
    static readonly IDictionary<Location, string> LOCATION_TO_TIME_ZONE_ID = new Dictionary<
        Location,
        string
    >
    {
        [Location.NewYork] = "Eastern Standard Time",
        [Location.London] = "GMT Standard Time",
        [Location.Paris] = "W. Europe Standard Time"
    };
    static readonly IDictionary<Location, string> LOCATION_TO_CULTURE_NAME = new Dictionary<
        Location,
        string
    >
    {
        [Location.NewYork] = "en-US",
        [Location.London] = "en-GB",
        [Location.Paris] = "fr-FR"
    };
    static readonly IDictionary<AlertLevel, TimeSpan> ALERT_LEVEL_TO_TIME_BEFORE = new Dictionary<
        AlertLevel,
        TimeSpan
    >
    {
        [AlertLevel.Early] = TimeSpan.FromDays(1),
        [AlertLevel.Standard] = new TimeSpan(1, 45, 0),
        [AlertLevel.Late] = TimeSpan.FromMinutes(30)
    };

    public static DateTime ShowLocalTime(DateTime dtUtc) => dtUtc.ToLocalTime();

    public static DateTime Schedule(string appointmentDateDescription, Location location) =>
        TimeZoneInfo.ConvertTimeToUtc(
            DateTime.Parse(appointmentDateDescription),
            TimeZoneInfo.FindSystemTimeZoneById(LOCATION_TO_TIME_ZONE_ID[location])
        );

    public static DateTime GetAlertTime(DateTime appointment, AlertLevel alertLevel) =>
        appointment - ALERT_LEVEL_TO_TIME_BEFORE[alertLevel];

    public static bool HasDaylightSavingChanged(DateTime dt, Location location) =>
        TimeZoneInfo
            .FindSystemTimeZoneById(LOCATION_TO_TIME_ZONE_ID[location])
            .IsDaylightSavingTime(dt)
        != TimeZoneInfo
            .FindSystemTimeZoneById(LOCATION_TO_TIME_ZONE_ID[location])
            .IsDaylightSavingTime(dt - TimeSpan.FromDays(7));

    public static DateTime NormalizeDateTime(string dtStr, Location location)
    {
        try
        {
            return DateTime.Parse(dtStr, new CultureInfo(LOCATION_TO_CULTURE_NAME[location]));
        }
        catch (FormatException)
        {
            return new DateTime(1, 1, 1);
        }
    }
}
