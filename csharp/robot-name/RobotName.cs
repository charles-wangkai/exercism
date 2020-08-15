using System.Collections.Generic;
using System;

public class Robot
{
    const int MAX_NAME_NUM = 26 * 26 * 10 * 10 * 10;

    static Random random = new Random();
    static ISet<string> names = new HashSet<string>();

    public Robot()
    {
        Reset();
    }

    public string Name { get; private set; }

    public void Reset()
    {
        if (names.Count == MAX_NAME_NUM)
        {
            throw new InvalidOperationException();
        }

        string name;
        do
        {
            name = $"{GenerateLetter()}{GenerateLetter()}{GenerateNumber():000}";
        } while (!names.Add(name));

        Name = name;
    }

    char GenerateLetter()
    {
        return (char)(random.Next(26) + 'A');
    }

    int GenerateNumber()
    {
        return random.Next(1000);
    }
}