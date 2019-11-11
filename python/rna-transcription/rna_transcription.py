def to_rna(dna_strand):
    return dna_strand.translate(str.maketrans('GCTA', 'CGAU')) if all(map(lambda dna: dna in 'ACGT', dna_strand)) else ''