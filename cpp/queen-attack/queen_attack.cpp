#include "queen_attack.h"

namespace queen_attack
{
chess_board::chess_board(const std::pair<int, int> &white, const std::pair<int, int> &black)
    : white_{white}, black_{black}
{
    if (white == black)
    {
        throw std::domain_error{"Queens can not be at the same position!"};
    }
}

chess_board::operator std::string() const
{
    std::string result;
    for (int r{0}; r < SIZE; ++r)
    {
        for (int c{0}; c < SIZE; ++c)
        {
            if (c != 0)
            {
                result += ' ';
            }

            if (std::make_pair(r, c) == white_)
            {
                result += 'W';
            }
            else if (std::make_pair(r, c) == black_)
            {
                result += 'B';
            }
            else
            {
                result += '_';
            }
        }

        result += '\n';
    }

    return result;
}

const std::pair<int, int> &chess_board::white() const { return white_; }

const std::pair<int, int> &chess_board::black() const { return black_; }

bool chess_board::can_attack() const
{
    return white_.first == black_.first ||
           white_.second == black_.second ||
           white_.first + white_.second == black_.first + black_.second ||
           white_.first - white_.second == black_.first - black_.second;
}
} // namespace queen_attack
