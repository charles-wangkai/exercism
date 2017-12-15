class Matrix(object):
    def __init__(self, matrix_string):
        lines = matrix_string.splitlines()
        self.rows = [list(map(int, line.split())) for line in lines]
        self.columns = [[int(line.split()[j]) for line in lines]
                        for j in range(len(lines[0].split()))]
