#if !defined(BEER_SONG_H)
#define BEER_SONG_H

#include <string>

namespace beer_song
{
std::string verse(int n);
std::string sing(int n1, int n2 = 0);
} // namespace beer_song

#endif // BEER_SONG_H