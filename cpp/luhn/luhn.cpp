#include "luhn.h"
#include <iostream>

namespace luhn
{
bool valid(std::string s)
{
    s.erase(std::remove(s.begin(), s.end(), ' '), s.end());

    if (s.length() <= 1 || !std::all_of(s.begin(), s.end(), [](char ch) { return std::isdigit(ch); }))
    {
        return false;
    }

    int sum{0};
    for (std::string::size_type i{0}; i < s.length(); ++i)
    {
        int value{s[i] - '0'};
        if ((s.length() - 1 - i) % 2)
        {
            value *= 2;
            if (value > 9)
            {
                value -= 9;
            }
        }

        sum += value;
    }

    return sum % 10 == 0;
}
} // namespace luhn
