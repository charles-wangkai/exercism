#include "rna_transcription.h"
#include <unordered_map>

namespace rna_transcription
{
std::unordered_map<char, char> DNA_TO_RNA{
    {'A', 'U'},
    {'C', 'G'},
    {'G', 'C'},
    {'T', 'A'}};

char to_rna(char dna)
{
    return DNA_TO_RNA[dna];
}

std::string to_rna(const std::string &dna_strand)
{
    std::string result;
    for (char dna : dna_strand)
    {
        result.push_back(DNA_TO_RNA[dna]);
    }

    return result;
}
} // namespace rna_transcription
