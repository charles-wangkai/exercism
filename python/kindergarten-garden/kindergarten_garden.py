class Garden(object):
    DEFAULT_STUDENTS = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
    CODE2NAME = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

    def __init__(self, diagram, students=None):
        if students is None:
            students = self.DEFAULT_STUDENTS
        
        self.garden = diagram.splitlines()
        self.students = sorted(students)

    def plants(self, student):
        index = self.students.index(student)
        return [self.CODE2NAME[code] for code in [self.garden[0][index * 2], self.garden[0][index * 2 + 1], self.garden[1][index * 2], self.garden[1][index * 2 + 1]]]
