#include "secret_handshake.h"
#include <array>

namespace secret_handshake
{
const std::array<std::string, 4> SIGNALS{"wink", "double blink", "close your eyes", "jump"};

std::vector<std::string> commands(int n)
{
    std::vector<std::string> result;
    int rest{n};
    for (const auto &signal : SIGNALS)
    {
        if (rest & 1)
        {
            result.push_back(signal);
        }
        rest >>= 1;
    }

    if (n >= 16)
    {
        std::reverse(result.begin(), result.end());
    }

    return result;
}
} // namespace secret_handshake
