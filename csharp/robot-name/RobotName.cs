using System.Collections.Generic;
using System;

public class Robot
{
    static Random random = new Random();
    static ISet<string> names = new HashSet<string>();

    string _name;

    public string Name
    {
        get
        {
            if (_name == null)
            {
                do
                {
                    _name = $"{GenerateLetter()}{GenerateLetter()}{GenerateDigit()}{GenerateDigit()}{GenerateDigit()}";
                } while (!names.Add(_name));
            }

            return _name;
        }
    }

    public void Reset()
    {
        names.Remove(_name);
        _name = null;
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