#if !defined(NUCLEOTIDE_COUNT_H)
#define NUCLEOTIDE_COUNT_H

#include <string>
#include <map>
#include <array>

namespace nucleotide_count
{
class counter
{
private:
    static constexpr std::array<char, 4> SYMBOLS{'A', 'C', 'G', 'T'};
    std::string dna;

    static bool is_valid_symbol(char symbol);

public:
    counter(const std::string &dna);
    std::map<char, int> nucleotide_counts() const;
    int count(char symbol) const;
};
} // namespace nucleotide_count

#endif // NUCLEOTIDE_COUNT_H