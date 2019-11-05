class Garden(object):
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
        if students is None:
            students = self.DEFAULT_STUDENTS

        students = sorted(students)
        garden = diagram.splitlines()

        self.student_to_plants = {}
        for i in range(len(garden[0]) // 2):
            self.student_to_plants[students[i]] = [self.CODE_TO_NAME[code] for code in [
                garden[0][i * 2],
                garden[0][i * 2 + 1],
                garden[1][i * 2],
                garden[1][i * 2 + 1]
            ]]

    def plants(self, student):
        return self.student_to_plants[student]
