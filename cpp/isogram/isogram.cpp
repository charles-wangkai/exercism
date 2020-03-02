#include "isogram.h"
#include <unordered_set>

namespace isogram
{
bool is_isogram(const std::string &s)
{
    std::unordered_set<char> seen;
    for (char ch : s)
    {
        if (std::isalpha(ch))
        {
            ch = std::tolower(ch);
            if (seen.count(ch) == 1)
            {
                return false;
            }

            seen.insert(ch);
        }
    }

    return true;
}
} // namespace isogram
