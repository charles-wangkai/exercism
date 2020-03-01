#include "clock.h"
#include <sstream>
#include <iomanip>

namespace date_independent
{
clock::clock(int total_minute) : total_minute{total_minute} {}

clock::operator std::string() const
{
    std::ostringstream ss;
    ss << std::setw(2) << std::setfill('0') << total_minute / 60;
    ss << ":";
    ss << std::setw(2) << std::setfill('0') << total_minute % 60;

    return ss.str();
}

clock clock::at(int hour, int minute)
{
    return clock{((hour * 60 + minute) % 1440 + 1440) % 1440};
}

clock clock::plus(int minute)
{
    return clock::at(0, total_minute + minute);
}

bool operator==(const clock &clock1, const clock &clock2)
{
    return clock1.total_minute == clock2.total_minute;
}

bool operator!=(const clock &clock1, const clock &clock2)
{
    return !(clock1 == clock2);
}
} // namespace date_independent
