using System.Linq;
using System;
using System.Collections.Generic;

public static class NucleotideCount
{
    static readonly HashSet<char> SYMBOLS = new HashSet<char> { 'A', 'C', 'G', 'T' };

    public static IDictionary<char, int> Count(string sequence)
    {
        var symbolToCount = SYMBOLS.ToDictionary(symbol => symbol, symbol => 0);

        foreach (char ch in sequence)
        {
            if (!SYMBOLS.Contains(ch))
            {
                throw new ArgumentException();
            }

            ++symbolToCount[ch];
        }

        return symbolToCount;
    }
}