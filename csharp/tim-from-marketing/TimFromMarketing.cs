using System;
using System.Text;

static class Badge
{
    public static string Print(int? id, string name, string? department)
    {
        StringBuilder result = new StringBuilder();
        if (id != null)
        {
            result.Append($"[{id}] - ");
        }
        result.Append(name);
        result.Append(" - ");
        result.Append((department ?? "owner").ToUpper());

        return result.ToString();
    }
}
