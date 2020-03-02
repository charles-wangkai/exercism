#include "acronym.h"
#include <regex>

namespace acronym
{
std::string acronym(std::string words)
{
    std::string result;

    std::regex r{R"([a-zA-Z0-9']+)"};
    std::smatch sm;
    while (std::regex_search(words, sm, r))
    {
        result += std::toupper(sm.str()[0]);

        words = sm.suffix();
    }

    return result;
}
} // namespace acronym
