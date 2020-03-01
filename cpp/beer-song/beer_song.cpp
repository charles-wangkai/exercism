#include "beer_song.h"

namespace beer_song
{
std::string verse(int n)
{
    if (n == 0)
    {
        return "No more bottles of beer on the wall, no more bottles of beer.\n"
               "Go to the store and buy some more, 99 bottles of beer on the wall.\n";
    }
    else if (n == 1)
    {
        return "1 bottle of beer on the wall, 1 bottle of beer.\n"
               "Take it down and pass it around, no more bottles of beer on the wall.\n";
    }

    return std::to_string(n) + " bottles of beer on the wall, " + std::to_string(n) + " bottles of beer.\n" +
           "Take one down and pass it around, " + std::to_string(n - 1) + " bottle" + ((n == 2) ? "" : "s") + " of beer on the wall.\n";
}

std::string sing(int n1, int n2)
{
    std::string result;
    for (int i{n1}; i >= n2; --i)
    {
        if (!result.empty())
        {
            result += '\n';
        }

        result += verse(i);
    }

    return result;
}
} // namespace beer_song
