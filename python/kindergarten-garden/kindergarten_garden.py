class Garden:
    _DEFAULT_STUDENTS = [
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
    _CODE_TO_NAME = {
        'G': 'Grass',
        'C': 'Clover',
        'R': 'Radishes',
        'V': 'Violets'
    }

    def __init__(self, diagram, students=None):
        students = sorted(students if students is not None
                          else self._DEFAULT_STUDENTS)

        front, back = diagram.splitlines()
        codes = zip(front[::2], front[1::2], back[::2], back[1::2])

        self._student_to_plants = {student: [self._CODE_TO_NAME[code] for code in codes]
                                   for student, codes in zip(students, codes)}

    def plants(self, student):
        return self._student_to_plants[student]
