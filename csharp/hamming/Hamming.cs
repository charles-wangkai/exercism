using System.Linq;
using System;

public static class Hamming
{
    public static int Distance(string firstStrand, string secondStrand)
    {
        if (firstStrand.Length != secondStrand.Length)
        {
            throw new ArgumentException();
        }

        return Enumerable
            .Range(0, firstStrand.Length)
            .Where(i => firstStrand[i] != secondStrand[i])
            .Count();
    }
}
