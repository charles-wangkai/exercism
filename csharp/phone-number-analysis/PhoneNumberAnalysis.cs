using System;

public static class PhoneNumber
{
    public static (bool IsNewYork, bool IsFake, string LocalNumber) Analyze(string phoneNumber)
    {
        return (
            phoneNumber.Substring(0, 3).Equals("212"),
            phoneNumber.Substring(4, 3).Equals("555"),
            phoneNumber.Substring(8)
        );
    }

    public static bool IsFake((bool IsNewYork, bool IsFake, string LocalNumber) phoneNumberInfo)
    {
        return phoneNumberInfo.IsFake;
    }
}
