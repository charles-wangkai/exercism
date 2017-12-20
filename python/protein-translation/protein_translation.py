CODON_LENGTH = 3
CODON_2_PROTEIN = {'AUG': 'Methionine', 'UUU': 'Phenylalanine', 'UUC': 'Phenylalanine', 'UUA': 'Leucine', 'UUG': 'Leucine', 'UCU': 'Serine', 'UCC': 'Serine', 'UCA': 'Serine',
                   'UCG': 'Serine', 'UAU': 'Tyrosine', 'UAC': 'Tyrosine', 'UGU': 'Cysteine', 'UGC': 'Cysteine', 'UGG': 'Tryptophan', 'UAA': 'STOP', 'UAG': 'STOP', 'UGA': 'STOP'}


def proteins(strand):
    proteins = []
    for i in range(0, len(strand), CODON_LENGTH):
        protein = CODON_2_PROTEIN[strand[i:i + CODON_LENGTH]]
        if protein == 'STOP':
            break
        proteins.append(protein)
    return proteins
