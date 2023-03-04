using System.Linq;
using System;
using System.Collections.Generic;

public static class NucleotideCount
{
    static readonly ISet<char> SYMBOLS = new HashSet<char> { 'A', 'C', 'G', 'T' };

    public static IDictionary<char, int> Count(string sequence)
    {
        IDictionary<char, int> symbolToCount = SYMBOLS.ToDictionary(symbol => symbol, symbol => 0);
        foreach (char c in sequence)
        {
            if (!SYMBOLS.Contains(c))
            {
                throw new ArgumentException();
            }

            ++symbolToCount[c];
        }

        return symbolToCount;
    }
}
