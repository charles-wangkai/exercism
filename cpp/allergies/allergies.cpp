#include "allergies.h"

namespace allergies
{
const std::unordered_map<std::string, int> allergy_test::ITEM_TO_SCORE{
    {"eggs", 1},
    {"peanuts", 2},
    {"shellfish", 4},
    {"strawberries", 8},
    {"tomatoes", 16},
    {"chocolate", 32},
    {"pollen", 64},
    {"cats", 128}};

allergy_test::allergy_test(int score) : score{score} {}

bool allergy_test::is_allergic_to(const std::string &item)
{
    return (score & ITEM_TO_SCORE.at(item)) != 0;
}

std::unordered_set<std::string> allergy_test::get_allergies()
{
    std::unordered_set<std::string> result;
    for (const auto &entry : ITEM_TO_SCORE)
    {
        if (is_allergic_to(entry.first))
        {
            result.insert(entry.first);
        }
    }

    return result;
}
} // namespace allergies
