//
// This is only a SKELETON file for the 'Wordy' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class ArgumentError extends Error {}

export class WordProblem {
  constructor(question) {
    this.question = question;
  }

  answer() {
    const fields = this.question.split(/What is|by|\?| /).filter(x => x != "");

    if (fields.length % 2 === 0) {
      throw new ArgumentError();
    }

    let [operand, ...restFields] = fields;
    let result = convertToNumber(operand);

    while (restFields.length) {
      const [operator, operand, ...rest] = restFields;
      restFields = rest;

      result = compute(result, operator, convertToNumber(operand));
    }

    return result;
  }
}

const OPERATIONS = {
  plus: (x, y) => x + y,
  minus: (x, y) => x - y,
  multiplied: (x, y) => x * y,
  divided: (x, y) => x / y
};

function compute(x, operator, y) {
  if (Object.prototype.hasOwnProperty.call(OPERATIONS, operator)) {
    return OPERATIONS[operator](x, y);
  }

  throw new ArgumentError();
}

function convertToNumber(s) {
  const result = Number(s);
  if (Number.isNaN(result)) {
    throw new ArgumentError();
  }

  return result;
}
