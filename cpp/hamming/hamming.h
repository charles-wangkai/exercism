#if !defined(HAMMING_H)
#define HAMMING_H

#include <string>

namespace hamming
{
int compute(const std::string &strand_a, const std::string &strand_b);
} // namespace hamming

#endif // HAMMING_H