//
// This is only a SKELETON file for the 'List - Ops' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class List {
  constructor(values = []) {
    this.values = values;
  }

  append(list) {
    this.values.push(...list.values);

    return this;
  }

  concat(lists) {
    for (const list of lists.values) {
      this.append(list);
    }

    return this;
  }

  filter(callback) {
    return new List(this.values.filter(callback));
  }

  map(callback) {
    return new List(this.values.map(callback));
  }

  length() {
    return this.values.length;
  }

  foldl(callback, initialValue) {
    return this.values.reduce(callback, initialValue);
  }

  foldr(callback, initialValue) {
    return this.values.reduceRight(callback, initialValue);
  }

  reverse() {
    this.values.reverse();

    return this;
  }
}
