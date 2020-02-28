#include "food_chain.h"
#include <array>

namespace food_chain
{
const std::array<std::string, 9> ANIMALS{
    "",
    "fly",
    "spider",
    "bird",
    "cat",
    "dog",
    "goat",
    "cow",
    "horse"};

const std::array<std::string, 8> COMMENTS{
    "",
    "",
    "It wriggled and jiggled and tickled inside her.",
    "How absurd to swallow a bird!",
    "Imagine that, to swallow a cat!",
    "What a hog, to swallow a dog!",
    "Just opened her throat and swallowed a goat!",
    "I don't know how she swallowed a cow!"};

const std::array<std::string, 8> PARTS{
    "",
    "",
    "She swallowed the spider to catch the fly.",
    "She swallowed the bird to catch the spider that wriggled and jiggled and tickled inside her.",
    "She swallowed the cat to catch the bird.",
    "She swallowed the dog to catch the cat.",
    "She swallowed the goat to catch the dog.",
    "She swallowed the cow to catch the goat."};

std::string verse(int n)
{
    std::string result{"I know an old lady who swallowed a " + ANIMALS[n] + "."};
    if (n != 1 && n != 8)
    {
        result += "\n" + COMMENTS[n];
    }
    if (n != 8)
    {
        for (int i{n}; i > 1; --i)
        {
            result += "\n" + PARTS[i];
        }
    }
    result += std::string{"\n"} + ((n == 8) ? "She's dead, of course!" : "I don't know why she swallowed the fly. Perhaps she'll die.") + "\n";

    return result;
}

std::string verses(int begin, int end)
{
    std::string result;
    for (int i{begin}; i <= end; ++i)
    {
        result += verse(i) + "\n";
    }

    return result;
}

std::string sing()
{
    return verses(1, 8);
}
} // namespace food_chain
