#include "armstrong_numbers.h"
#include <string>

namespace armstrong_numbers
{
int pow(int base, int exponent)
{
    int result{1};
    for (int i{0}; i < exponent; ++i)
    {
        result *= base;
    }

    return result;
}

bool is_armstrong_number(int n)
{
    std::string s{std::to_string(n)};

    int sum{0};
    for (char digit : s)
    {
        sum += pow(digit - '0', s.length());
    }

    return sum == n;
}
} // namespace armstrong_numbers
