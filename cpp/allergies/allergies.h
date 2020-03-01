#if !defined(ALLERGIES_H)
#define ALLERGIES_H

#include <string>
#include <unordered_set>
#include <unordered_map>

namespace allergies
{
class allergy_test
{
private:
    static const std::unordered_map<std::string, int> ITEM_TO_SCORE;
    int score;

public:
    allergy_test(int score);
    bool is_allergic_to(const std::string &item);
    std::unordered_set<std::string> get_allergies();
};
} // namespace allergies

#endif // ALLERGIES_H