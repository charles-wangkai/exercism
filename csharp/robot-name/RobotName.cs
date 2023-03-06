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

        while (true)
        {
            string name =
                $"{GenerateLetter()}{GenerateLetter()}{GenerateDigit()}{GenerateDigit()}{GenerateDigit()}";
            if (!names.Contains(name))
            {
                names.Add(name);
                Name = name;

                break;
            }
        }
    }

    char GenerateLetter() => (char)(random.Next(26) + 'A');

    int GenerateDigit() => random.Next(10);
}
