//
// This is only a SKELETON file for the 'Grade School' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class GradeSchool {
  constructor() {
    this.gradeToNames = {};
  }

  roster() {
    return JSON.parse(JSON.stringify(this.gradeToNames));
  }

  add(name, grade) {
    if (!Object.prototype.hasOwnProperty.call(this.gradeToNames, grade)) {
      this.gradeToNames[grade] = [];
    }
    this.gradeToNames[grade].push(name);

    this.gradeToNames[grade].sort();
  }

  grade(grade) {
    return Object.prototype.hasOwnProperty.call(this.gradeToNames, grade)
      ? [...this.gradeToNames[grade]]
      : [];
  }
}
