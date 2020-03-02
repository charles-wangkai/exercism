#include "robot_simulator.h"
#include <unordered_map>

namespace robot_simulator
{
const std::unordered_map<Bearing, std::pair<int, int>> BEARING_TO_OFFSET{
    {Bearing::NORTH, std::make_pair(0, 1)},
    {Bearing::EAST, std::make_pair(1, 0)},
    {Bearing::SOUTH, std::make_pair(0, -1)},
    {Bearing::WEST, std::make_pair(-1, 0)}};

Robot::Robot(const std::pair<int, int> &position, Bearing bearing) : position{position}, bearing{bearing} {}

const std::pair<int, int> &Robot::get_position() const { return position; }

Bearing Robot::get_bearing() const { return bearing; }

void Robot::advance()
{
    position.first += BEARING_TO_OFFSET.at(bearing).first;
    position.second += BEARING_TO_OFFSET.at(bearing).second;
}

void Robot::turn_left()
{
    bearing = static_cast<Bearing>((static_cast<int>(bearing) - 1 + static_cast<int>(Bearing::ITEM_COUNT)) % static_cast<int>(Bearing::ITEM_COUNT));
}

void Robot::turn_right()
{
    bearing = static_cast<Bearing>((static_cast<int>(bearing) + 1) % static_cast<int>(Bearing::ITEM_COUNT));
}

void Robot::execute_sequence(const std::string &instructions)
{
    for (char instruction : instructions)
    {
        if (instruction == 'A')
        {
            advance();
        }
        else if (instruction == 'L')
        {
            turn_left();
        }
        else if (instruction == 'R')
        {
            turn_right();
        }
    }
}
} // namespace robot_simulator
