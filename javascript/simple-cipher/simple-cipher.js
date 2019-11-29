//
// This is only a SKELETON file for the 'Simple Cipher' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Cipher {
  constructor(key = this.generateKey()) {
    this._key = key;
  }

  generateKey() {
    return Array.from({ length: 100 }, () =>
      String.fromCharCode("a".charCodeAt() + Math.floor(Math.random() * 26))
    ).join("");
  }

  convertLetterToOffset(letter) {
    return letter.charCodeAt() - "a".charCodeAt();
  }

  convertOffsetToLetter(offset) {
    return String.fromCharCode(offset + "a".charCodeAt());
  }

  encode(text) {
    return Array.from(text, (letter, i) =>
      this.convertOffsetToLetter(
        (this.convertLetterToOffset(letter) +
          this.convertLetterToOffset(this._key[i % this._key.length])) %
          26
      )
    ).join("");
  }

  decode(text) {
    return Array.from(text, (letter, i) =>
      this.convertOffsetToLetter(
        (this.convertLetterToOffset(letter) -
          this.convertLetterToOffset(this._key[i % this._key.length]) +
          26) %
          26
      )
    ).join("");
  }

  get key() {
    return this._key;
  }
}
