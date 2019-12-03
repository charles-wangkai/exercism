//
// This is only a SKELETON file for the 'Simple Cipher' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const KEY = Symbol("key");

const ALPHABET = {
  size: 26,
  firstCharCode: "a".charCodeAt()
};

export class Cipher {
  constructor(key = generateKey()) {
    this[KEY] = key;
  }

  encode(text) {
    return Array.from(text, (letter, i) =>
      convertOffsetToLetter(
        (convertLetterToOffset(letter) +
          convertLetterToOffset(this[KEY][i % this[KEY].length])) %
          ALPHABET.size
      )
    ).join("");
  }

  decode(text) {
    return Array.from(text, (letter, i) =>
      convertOffsetToLetter(
        (convertLetterToOffset(letter) -
          convertLetterToOffset(this[KEY][i % this[KEY].length]) +
          ALPHABET.size) %
          ALPHABET.size
      )
    ).join("");
  }

  get key() {
    return this[KEY];
  }
}

function generateKey() {
  return Array.from({ length: 100 }, () =>
    String.fromCharCode(
      ALPHABET.firstCharCode + Math.floor(Math.random() * ALPHABET.size)
    )
  ).join("");
}

function convertLetterToOffset(letter) {
  return letter.charCodeAt() - ALPHABET.firstCharCode;
}

function convertOffsetToLetter(offset) {
  return String.fromCharCode(offset + ALPHABET.firstCharCode);
}
