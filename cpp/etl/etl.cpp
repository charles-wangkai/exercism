#include "etl.h"
#include <cctype>

namespace etl
{
std::map<char, int> transform(const std::map<int, std::vector<char>> &legacy_data)
{
    std::map<char, int> result;
    for (const auto &it : legacy_data)
    {
        for (char letter : it.second)
        {
            result[std::tolower(letter)] = it.first;
        }
    }

    return result;
}
} // namespace etl
