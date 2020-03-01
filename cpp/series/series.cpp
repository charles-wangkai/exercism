#include "series.h"

namespace series
{
std::vector<int> digits(const std::string &s)
{
    std::vector<int> result;
    for (char ch : s)
    {
        result.push_back(ch - '0');
    }

    return result;
}

std::vector<std::vector<int>> slice(const std::string &series, std::vector<std::string>::size_type length)
{
    if (length > series.size())
    {
        throw std::domain_error{"Invalid length!"};
    }

    std::vector<std::vector<int>> result;
    for (int i{0}; i + length <= series.size(); ++i)
    {
        result.push_back(digits(series.substr(i, length)));
    }

    return result;
}
} // namespace series
