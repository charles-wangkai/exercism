class School(object):

    def __init__(self):
        self.name_to_grade = {}

    def add_student(self, name, grade):
        self.name_to_grade[name] = grade

    def grade(self, grade):
        return sorted(filter(lambda name: self.name_to_grade[name] == grade,
                             self.name_to_grade))

    def roster(self):
        return [name
                for grade in sorted(set(self.name_to_grade.values()))
                for name in self.grade(grade)]
