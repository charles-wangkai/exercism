#include "gigasecond.h"

namespace gigasecond
{
boost::posix_time::ptime advance(const boost::posix_time::ptime t)
{
    return t + boost::posix_time::seconds(1'000'000'000);
}
} // namespace gigasecond
