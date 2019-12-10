#include "pangram.h"
#include <bitset>
#include <cctype>

namespace pangram
{
bool is_pangram(const std::string &s)
{
    std::bitset<26> appeared;
    for (const char &ch : s)
    {
        if (std::isalpha(ch))
        {
            appeared.set(std::tolower(ch) - 'a');
        }
    }

    return appeared.all();
}
} // namespace pangram
