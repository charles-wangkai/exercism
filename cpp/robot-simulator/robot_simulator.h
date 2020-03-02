#if !defined(ROBOT_SIMULATOR_H)
#define ROBOT_SIMULATOR_H

#include <utility>
#include <string>

namespace robot_simulator
{
enum class Bearing
{
    NORTH,
    EAST,
    SOUTH,
    WEST,
    ITEM_COUNT
};

class Robot
{
private:
    std::pair<int, int> position;
    Bearing bearing;

public:
    Robot(const std::pair<int, int> &position = std::make_pair(0, 0), Bearing bearing = Bearing::NORTH);
    const std::pair<int, int> &get_position() const;
    Bearing get_bearing() const;
    void advance();
    void turn_left();
    void turn_right();
    void execute_sequence(const std::string &instructions);
};
} // namespace robot_simulator

#endif // ROBOT_SIMULATOR_H