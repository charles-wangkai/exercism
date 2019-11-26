//
// This is only a SKELETON file for the 'List - Ops' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export class List {
  constructor(a, b) {
    if (a === undefined) {
      this.last = null;
    } else if (a instanceof List) {
      this.prefix = a;
      this.last = b;
    } else {
      const length = b === undefined ? a.length : b;

      if (length === 0) {
        return new List();
      } else {
        return new List(new List(a, length - 1), a[length - 1]);
      }
    }
  }

  get values() {
    if (this.isEmpty()) {
      return [];
    }

    const result = this.prefix.values;
    result.push(this.last);

    return result;
  }

  isEmpty() {
    return this.last === null;
  }

  push(value) {
    return new List(this, value);
  }

  append(list) {
    return list.isEmpty() ? this : this.append(list.prefix).push(list.last);
  }

  concat(lists) {
    return lists.isEmpty()
      ? this
      : this.concat(lists.prefix).append(lists.last);
  }

  filter(callback) {
    return this.isEmpty()
      ? this
      : callback(this.last)
      ? new List(this.prefix.filter(callback), this.last)
      : this.prefix.filter(callback);
  }

  map(callback) {
    return this.isEmpty()
      ? this
      : new List(this.prefix.map(callback), callback(this.last));
  }

  length() {
    return this.isEmpty() ? 0 : this.prefix.length() + 1;
  }

  foldl(callback, initialValue) {
    return this.reverse().foldr(callback, initialValue);
  }

  foldr(callback, initialValue) {
    return this.isEmpty()
      ? initialValue
      : this.prefix.foldr(callback, callback(initialValue, this.last));
  }

  reverse() {
    return this.isEmpty()
      ? this
      : new List().push(this.last).append(this.prefix.reverse());
  }
}
