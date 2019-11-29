// This is only a SKELETON file for the 'Robot Name' exercise. It's been
// provided as a convenience to get your started writing code faster.

export class Robot {
  constructor() {
    this.reset();
  }

  reset() {
    this._name = Robot.availableNames.pop();
  }

  get name() {
    return this._name;
  }
}

function shuffle(a) {
  for (let i = a.length - 1; i >= 0; i--) {
    const otherIndex = Math.floor(Math.random() * (i + 1));

    [a[i], a[otherIndex]] = [a[otherIndex], a[i]];
  }

  return a;
}

function buildAllNames() {
  const names = [];
  for (let a = 0; a < 26; a++) {
    const chA = String.fromCharCode("A".charCodeAt(0) + a);
    for (let b = 0; b < 26; b++) {
      const chB = String.fromCharCode("A".charCodeAt(0) + b);
      for (let c = 0; c < 10; c++) {
        for (let d = 0; d < 10; d++) {
          for (let e = 0; e < 10; e++) {
            names.push(`${chA}${chB}${c}${d}${e}`);
          }
        }
      }
    }
  }

  shuffle(names);

  return names;
}

Robot.availableNames = buildAllNames();

Robot.releaseNames = () => {
  Robot.availableNames = buildAllNames();
};
