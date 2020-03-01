#if !defined(QUEEN_ATTACK_H)
#define QUEEN_ATTACK_H

#include <utility>
#include <string>

namespace queen_attack
{
class chess_board
{
private:
    static constexpr int SIZE{8};
    std::pair<int, int> white_;
    std::pair<int, int> black_;

public:
    chess_board(const std::pair<int, int> &white = std::make_pair(0, 3), const std::pair<int, int> &black = std::make_pair(7, 3));
    operator std::string() const;
    const std::pair<int, int> &white() const;
    const std::pair<int, int> &black() const;
    bool can_attack() const;
};
} // namespace queen_attack

#endif // QUEEN_ATTACK_H