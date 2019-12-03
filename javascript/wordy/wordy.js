//
// This is only a SKELETON file for the 'Wordy' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

class ArgumentError extends Error {}

class Wordy {
  constructor(question) {
    this.question = question;
  }

  answer() {
    const fields = this.question.split(/What is|by|\?| /).filter(x => x != "");

    if (fields.length % 2 === 0) {
      throw new ArgumentError();
    }

    let result = convertToNumber(fields[0]);
    for (let i = 1; i < fields.length; i += 2) {
      result = compute(result, fields[i], convertToNumber(fields[i + 1]));
    }

    return result;
  }
}

function compute(x, operation, y) {
  if (operation === "plus") {
    return x + y;
  } else if (operation === "minus") {
    return x - y;
  } else if (operation === "multiplied") {
    return x * y;
  } else if (operation === "divided") {
    return x / y;
  } else {
    throw new ArgumentError();
  }
}

function convertToNumber(s) {
  const result = Number(s);
  if (Number.isNaN(result)) {
    throw new ArgumentError();
  }

  return result;
}

export { Wordy as WordProblem, ArgumentError };
