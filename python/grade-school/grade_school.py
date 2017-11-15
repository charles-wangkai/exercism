class School(object):

    def __init__(self, name):
        self.name = name
        self.student2grade = {}

    def add(self, student, grade):
        self.student2grade[student] = grade

    def grade(self, grade):
        return tuple(student for student in self.student2grade if self.student2grade[student] == grade)

    def sort(self):
        return [(grade, tuple(sorted(self.grade(grade)))) for grade in sorted(set(self.student2grade.values()))]
