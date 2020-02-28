#include "robot_name.h"
#include <ctime>

namespace robot_name
{
bool robot::seeded{false};
std::unordered_set<std::string> robot::names;

char generate_letter()
{
    return std::rand() % 26 + 'A';
}

int generate_digit()
{
    return std::rand() % 10;
}

std::string robot::find_name()
{
    if (!seeded)
    {
        std::srand(std::time(nullptr));
        seeded = true;
    }

    while (true)
    {
        std::string name{std::string{} + generate_letter() + generate_letter() + std::to_string(generate_digit()) + std::to_string(generate_digit()) + std::to_string(generate_digit())};

        if (names.count(name) == 0)
        {
            names.insert(name);

            return name;
        }
    }
}

robot::robot() : name_{find_name()} {}

const std::string &robot::name() const
{
    return name_;
}

void robot::reset()
{
    name_ = find_name();
}
} // namespace robot_name
