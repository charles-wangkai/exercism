class Matrix(object):
    def __init__(self, matrix_string):
        lines = matrix_string.splitlines()
        self.rows = [list(map(int, line.split())) for line in lines]
        self.columns = [[int(line.split()[j]) for line in lines]
                        for j in range(len(lines[0].split()))]

    def row(self, row_number):
        return self.rows[row_number - 1]

    def column(self, column_number):
        return self.columns[column_number - 1]
