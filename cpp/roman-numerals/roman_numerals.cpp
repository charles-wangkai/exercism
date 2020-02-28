#include "roman_numerals.h"
#include <vector>

namespace roman_numerals
{
const std::vector<std::pair<int, std::string>> VALUE_SYMBOLS{
    {1000, "M"},
    {900, "CM"},
    {500, "D"},
    {400, "CD"},
    {100, "C"},
    {90, "XC"},
    {50, "L"},
    {40, "XL"},
    {10, "X"},
    {9, "IX"},
    {5, "V"},
    {4, "IV"},
    {1, "I"}};

std::string convert(int number)
{
    std::string result;
    for (const auto &value_symbol : VALUE_SYMBOLS)
    {
        while (number >= value_symbol.first)
        {
            result += value_symbol.second;
            number -= value_symbol.first;
        }
    }

    return result;
}
} // namespace roman_numerals
