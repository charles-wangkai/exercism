//
// This is only a SKELETON file for the 'List - Ops' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class List {
  constructor(values = []) {
    this.values = values;
  }

  push(value) {
    this.values[this.values.length] = value;
  }

  append(list) {
    for (const value of list.values) {
      this.push(value);
    }

    return this;
  }

  concat(lists) {
    for (const list of lists.values) {
      this.append(list);
    }

    return this;
  }

  filter(callback) {
    const result = new List();
    for (const value of this.values) {
      if (callback(value)) {
        result.push(value);
      }
    }

    return result;
  }

  map(callback) {
    const result = new List();
    for (const value of this.values) {
      result.push(callback(value));
    }

    return result;
  }

  length() {
    return this.values.length;
  }

  foldl(callback, initialValue) {
    let result = initialValue;
    for (const value of this.values) {
      result = callback(result, value);
    }

    return result;
  }

  foldr(callback, initialValue) {
    return new List(this.values).reverse().foldl(callback, initialValue);
  }

  reverse() {
    for (let i = 0, j = this.values.length - 1; i < j; i++, j--) {
      const temp = this.values[i];
      this.values[i] = this.values[j];
      this.values[j] = temp;
    }

    return this;
  }
}
