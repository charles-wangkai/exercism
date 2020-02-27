#if !defined(ANAGRAM_H)
#define ANAGRAM_H

#include <string>
#include <vector>

namespace anagram
{
class anagram
{
private:
    std::string word;

public:
    anagram(const std::string &word);
    std::vector<std::string> matches(const std::vector<std::string> &candidates);
};
} // namespace anagram

#endif // ANAGRAM_H