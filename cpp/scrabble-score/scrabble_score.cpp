#include "scrabble_score.h"
#include <unordered_map>
#include <vector>

namespace scrabble_score
{
const std::unordered_map<int, std::vector<char>> VALUE_TO_LETTERS{
    {1, {'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'}},
    {2, {'D', 'G'}},
    {3, {'B', 'C', 'M', 'P'}},
    {4, {'F', 'H', 'V', 'W', 'Y'}},
    {5, {'K'}},
    {8, {'J', 'X'}},
    {10, {'Q', 'Z'}}};

std::unordered_map<char, int> build_letter_to_value()
{
    std::unordered_map<char, int> letter_to_value;
    for (const auto &p : VALUE_TO_LETTERS)
    {
        for (char letter : p.second)
        {
            letter_to_value[letter] = p.first;
        }
    }

    return letter_to_value;
}

const std::unordered_map<char, int> LETTER_TO_VALUE{build_letter_to_value()};

int score(const std::string &word)
{
    int result{0};
    for (char letter : word)
    {
        result += LETTER_TO_VALUE.at(std::toupper(letter));
    }

    return result;
}
} // namespace scrabble_score
