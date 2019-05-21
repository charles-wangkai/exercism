class Matrix(object):
    def __init__(self, matrix_string):
        lines = matrix_string.splitlines()
        self.rows = [[int(s) for s in line.split()]
                     for line in lines]
        self.columns = [[row[j] for row in self.rows]
                        for j in range(len(self.rows[0]))]

    def row(self, row_number):
        return self.rows[row_number - 1]

    def column(self, column_number):
        return self.columns[column_number - 1]
