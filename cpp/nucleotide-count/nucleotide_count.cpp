#include "nucleotide_count.h"

namespace nucleotide_count
{
constexpr std::array<char, 4> counter::SYMBOLS;

bool counter::is_valid_symbol(char symbol)
{
    return std::any_of(SYMBOLS.begin(), SYMBOLS.end(), [&](char s) { return s == symbol; });
}

counter::counter(const std::string &dna) : dna{dna}
{
    if (!std::all_of(dna.begin(), dna.end(), [](char symbol) { return is_valid_symbol(symbol); }))
    {
        throw std::invalid_argument("Invalid dna");
    }
}

std::map<char, int> counter::nucleotide_counts() const
{
    std::map<char, int> symbol_to_count;
    for (char symbol : SYMBOLS)
    {
        symbol_to_count[symbol] = count(symbol);
    }

    return symbol_to_count;
}

int counter::count(char symbol) const
{
    if (!is_valid_symbol(symbol))
    {
        throw std::invalid_argument("Invalid symbol");
    }

    return std::count_if(dna.begin(), dna.end(), [&](char ch) { return ch == symbol; });
}
} // namespace nucleotide_count
