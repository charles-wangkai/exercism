#if !defined(WORD_COUNT_H)
#define WORD_COUNT_H

#include <string>
#include <map>

namespace word_count
{
std::map<std::string, int> words(std::string phrase);
} // namespace word_count

#endif // WORD_COUNT_H