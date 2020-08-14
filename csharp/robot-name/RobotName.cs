using System.Collections.Generic;
using System;

public class Robot
{
    const int MAX_NAME_NUM = 26 * 26 * 10 * 10 * 10;

    static Random random = new Random();
    static ISet<string> names = new HashSet<string>();

    public Robot()
    {
        if (names.Count == MAX_NAME_NUM)
        {
            throw new InvalidOperationException();
        }

        Reset();
    }

    public string Name { get; private set; }

    public void Reset()
    {
        names.Remove(Name);

        do
        {
            Name = $"{GenerateLetter()}{GenerateLetter()}{GenerateDigit()}{GenerateDigit()}{GenerateDigit()}";
        } while (!names.Add(Name));
    }

    char GenerateLetter()
    {
        return (char)(random.Next(26) + 'A');
    }

    int GenerateDigit()
    {
        return random.Next(10);
    }
}