using System;
using System.Collections.Generic;

enum LogLevel
{
    Unknown = 0,
    Trace = 1,
    Debug = 2,
    Info = 4,
    Warning = 5,
    Error = 6,
    Fatal = 42
}

static class LogLine
{
    static readonly IDictionary<string, LogLevel> STR_TO_LOG_LEVEL = new Dictionary<
        string,
        LogLevel
    >
    {
        ["TRC"] = LogLevel.Trace,
        ["DBG"] = LogLevel.Debug,
        ["INF"] = LogLevel.Info,
        ["WRN"] = LogLevel.Warning,
        ["ERR"] = LogLevel.Error,
        ["FTL"] = LogLevel.Fatal
    };

    public static LogLevel ParseLogLevel(string logLine) =>
        STR_TO_LOG_LEVEL.TryGetValue(logLine.Substring(1, 3), out LogLevel logLevel)
            ? logLevel
            : LogLevel.Unknown;

    public static string OutputForShortLog(LogLevel logLevel, string message) =>
        $"{(int)logLevel}:{message}";
}
