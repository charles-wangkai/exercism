//
// This is only a SKELETON file for the 'Pascals Triangle' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Triangle {
  constructor(rowNum) {
    this._rows = [];
    for (let i = 0; i < rowNum; i++) {
      const row = [1];
      for (let j = 1; j < i; j++) {
        row.push(this._rows[i - 1][j - 1] + this._rows[i - 1][j]);
      }
      if (i != 0) {
        row.push(1);
      }

      this._rows.push(row);
    }
  }

  get lastRow() {
    return this._rows[this._rows.length - 1];
  }

  get rows() {
    return this._rows;
  }
}
