#if !defined(ROBOT_NAME_H)
#define ROBOT_NAME_H

#include <string>
#include <unordered_set>

namespace robot_name
{
class robot
{
private:
    static bool seeded;
    static std::unordered_set<std::string> names;
    std::string name_;

    static std::string find_name();

public:
    robot();
    const std::string &name() const;
    void reset();
};
} // namespace robot_name

#endif // ROBOT_NAME_H