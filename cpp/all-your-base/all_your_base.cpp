#include "all_your_base.h"

namespace all_your_base
{
std::vector<unsigned int> convert(int from_base, const std::vector<unsigned int> &digits, int to_base)
{
    if (from_base < 2 || to_base < 2)
    {
        throw std::invalid_argument{"Bases must be at least 2."};
    }

    if (!std::all_of(digits.begin(), digits.end(), [&](int digit) { return digit >= 0 && digit < from_base; }))
    {
        throw std::invalid_argument{"Digits are out of bound."};
    }

    int number{0};
    for (int digit : digits)
    {
        number = number * from_base + digit;
    }

    std::vector<unsigned int> result;
    while (number)
    {
        result.push_back(number % to_base);
        number /= to_base;
    }

    std::reverse(result.begin(), result.end());

    return result;
}
} // namespace all_your_base
