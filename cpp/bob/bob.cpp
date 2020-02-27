#include "bob.h"

namespace bob
{
bool is_question(const std::string &phrase)
{
    auto it{std::find_if(phrase.rbegin(), phrase.rend(), [](char ch) { return !std::isspace(ch); })};

    return it != phrase.rend() && *it == '?';
}

std::string hey(const std::string &phrase)
{
    if (std::all_of(phrase.begin(), phrase.end(), [](char ch) { return std::isspace(ch); }))
    {
        return "Fine. Be that way!";
    }

    bool phrase_is_question{is_question(phrase)};

    if (std::any_of(phrase.begin(), phrase.end(), [](char ch) { return std ::isupper(ch); }) &&
        std::all_of(phrase.begin(), phrase.end(), [](char ch) { return !std ::islower(ch); }))
    {
        if (phrase_is_question)
        {
            return "Calm down, I know what I'm doing!";
        }
        else
        {
            return "Whoa, chill out!";
        }
    }

    if (phrase_is_question)
    {
        return "Sure.";
    }

    return "Whatever.";
}
} // namespace bob
