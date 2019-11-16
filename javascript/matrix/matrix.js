//
// This is only a SKELETON file for the 'Matrix' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class Matrix {
  constructor(matrix_string) {
    const lines = matrix_string.split("\n");
    this._rows = lines.map(line => line.split(" ").map(s => Number(s)));
    this._columns = Array.from({ length: this._rows[0].length }, (x, i) =>
      this._rows.map(row => row[i])
    );
  }

  get rows() {
    return this._rows;
  }

  get columns() {
    return this._columns;
  }
}
