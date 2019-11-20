//
// This is only a SKELETON file for the 'Grade School' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class GradeSchool {
  constructor() {
    this.nameToGrade = {};
  }

  roster() {
    return Object.fromEntries(
      [...new Set(Object.values(this.nameToGrade))].map(grade => [
        grade,
        this.grade(grade)
      ])
    );
  }

  add(name, grade) {
    this.nameToGrade[name] = grade;
  }

  grade(grade) {
    return Object.keys(this.nameToGrade)
      .filter(name => this.nameToGrade[name] == grade)
      .sort();
  }
}
