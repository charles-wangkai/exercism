using System;

public static class LogAnalysis
{
    public static string SubstringAfter(this string log, string delimiter) =>
        log.Substring(log.IndexOf(delimiter) + delimiter.Length);

    public static string SubstringBetween(this string log, string delimiter1, string delimiter2) =>
        log.Substring(
            log.IndexOf(delimiter1) + delimiter1.Length,
            log.IndexOf(delimiter2) - log.IndexOf(delimiter1) - delimiter1.Length
        );

    public static string Message(this string log) => log.Substring(log.IndexOf(' ') + 1);

    public static string LogLevel(this string log) => log.Substring(1, log.IndexOf(']') - 1);
}
