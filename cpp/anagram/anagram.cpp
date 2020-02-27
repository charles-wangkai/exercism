#include "anagram.h"

namespace anagram
{
anagram::anagram(const std::string &word) : word{word} {}

std::string str_tolower(std::string s)
{
    std::transform(s.begin(), s.end(), s.begin(), [](char ch) { return std::tolower(ch); });

    return s;
}

std::string generate_key(std::string s)
{
    s = str_tolower(s);
    std::sort(s.begin(), s.end());

    return s;
}

std::vector<std::string> anagram::matches(const std::vector<std::string> &candidates)
{
    std::vector<std::string> result;
    for (const auto &candidate : candidates)
    {
        if (str_tolower(candidate) != str_tolower(word) && generate_key(candidate) == generate_key(word))
        {
            result.push_back(candidate);
        }
    }

    return result;
}
} // namespace anagram
