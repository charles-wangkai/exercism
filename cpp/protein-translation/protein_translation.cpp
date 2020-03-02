#include "protein_translation.h"
#include <unordered_map>

namespace protein_translation
{
constexpr int CODON_LENGTH{3};
const std::unordered_map<std::string, std::string> CODON_TO_PROTEIN{
    {"AUG", "Methionine"},
    {"UUU", "Phenylalanine"},
    {"UUC", "Phenylalanine"},
    {"UUA", "Leucine"},
    {"UUG", "Leucine"},
    {"UCU", "Serine"},
    {"UCC", "Serine"},
    {"UCA", "Serine"},
    {"UCG", "Serine"},
    {"UAU", "Tyrosine"},
    {"UAC", "Tyrosine"},
    {"UGU", "Cysteine"},
    {"UGC", "Cysteine"},
    {"UGG", "Tryptophan"},
    {"UAA", "STOP"},
    {"UAG", "STOP"},
    {"UGA", "STOP"}};

std::vector<std::string> proteins(const std::string &strand)
{
    std::vector<std::string> proteins;
    for (std::string::size_type i{0}; i < strand.length(); i += CODON_LENGTH)
    {
        std::string protein{CODON_TO_PROTEIN.at(strand.substr(i, CODON_LENGTH))};
        if (protein == "STOP")
        {
            break;
        }

        proteins.push_back(protein);
    }

    return proteins;
}
} // namespace protein_translation
