#include "say.h"
#include <array>
#include <vector>

namespace say
{
std::string join(const std::vector<std::string> &parts)
{
    std::string result;
    for (const auto &part : parts)
    {
        if (!result.empty())
        {
            result += " ";
        }

        result += part;
    }

    return result;
}

std::string say_within_thousand(int n)
{
    static const std::array<std::string, 10> ONE_NAMES{"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static const std::array<std::string, 10> TEN_NAMES{"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    static const std::array<std::string, 9> TEEN_NAMES{"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    std::vector<std::string> parts;

    int hundred{n / 100};
    if (hundred > 0)
    {
        parts.push_back(ONE_NAMES[hundred] + " hundred");
    }

    n %= 100;
    if (n > 0)
    {
        if (n >= 11 && n <= 19)
        {
            parts.push_back(TEEN_NAMES[n - 11]);
        }
        else
        {
            std::string part;

            int ten{n / 10};
            if (ten > 0)
            {
                part += TEN_NAMES[ten];
            }

            int one{n % 10};
            if (one > 0)
            {
                if (!part.empty())
                {
                    part += '-';
                }

                part += ONE_NAMES[one];
            }

            if (!part.empty())
            {
                parts.push_back(part);
            }
        }
    }

    return join(parts);
}

std::string in_english(long long n)
{
    static const std::array<std::string, 4> CHUNK_NAMES{"", " thousand", " million", " billion"};

    if (n < 0 || n > 999'999'999'999)
    {
        throw std::domain_error{"Invalid number!"};
    }

    if (n == 0)
    {
        return "zero";
    }

    std::vector<std::string> parts;
    long long rest{n};
    for (const auto &chunk_name : CHUNK_NAMES)
    {
        int chunk{static_cast<int>(rest % 1000)};
        if (chunk != 0)
        {
            parts.push_back(say_within_thousand(chunk) + chunk_name);
        }

        rest /= 1000;
    }
    std::reverse(parts.begin(), parts.end());

    return join(parts);
}
} // namespace say
