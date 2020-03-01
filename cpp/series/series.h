#if !defined(SERIES_H)
#define SERIES_H

#include <string>
#include <vector>

namespace series
{
std::vector<int> digits(const std::string &series);
std::vector<std::vector<int>> slice(const std::string &series, std::vector<std::string>::size_type length);
} // namespace series

#endif // SERIES_H