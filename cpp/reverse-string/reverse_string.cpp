#include "reverse_string.h"

namespace reverse_string
{
std::string reverse_string(const std::string &s)
{
    return std::string{s.rbegin(), s.rend()};
}
} // namespace reverse_string
