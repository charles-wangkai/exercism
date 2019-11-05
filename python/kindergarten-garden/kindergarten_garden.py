class Garden:
    DEFAULT_STUDENTS = [
        'Alice',
        'Bob',
        'Charlie',
        'David',
        'Eve',
        'Fred',
        'Ginny',
        'Harriet',
        'Ileana',
        'Joseph',
        'Kincaid',
        'Larry'
    ]
    CODE_TO_NAME = {
        'G': 'Grass',
        'C': 'Clover',
        'R': 'Radishes',
        'V': 'Violets'
    }

    def __init__(self, diagram, students=None):
        students = sorted(students if students is not None
                          else self.DEFAULT_STUDENTS)

        garden = diagram.splitlines()
        codes_list = [[self.CODE_TO_NAME[code] for code in codes]
                      for codes in zip(garden[0][::2], garden[0][1::2], garden[1][::2], garden[1][1::2])]

        self.student_to_plants = {student: codes
                                  for student, codes in zip(students, codes_list)}

    def plants(self, student):
        return self.student_to_plants[student]
