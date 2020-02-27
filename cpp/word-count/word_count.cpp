#include "word_count.h"
#include <regex>

namespace word_count
{
std::map<std::string, int> words(std::string phrase)
{
    std::map<std::string, int> result;

    std::transform(phrase.begin(), phrase.end(), phrase.begin(), [](char ch) { return std::tolower(ch); });

    std::regex r{R"([a-z0-9]+('[a-z0-9]+)*)"};
    std::smatch sm;
    while (std::regex_search(phrase, sm, r))
    {
        ++result[sm.str()];

        phrase = sm.suffix();
    }

    return result;
}
} // namespace word_count
