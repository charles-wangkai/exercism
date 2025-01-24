# Secrets

Welcome to Secrets on Exercism's Java Track.
If you need help running the tests or submitting your code, check out `HELP.md`.
If you get stuck on the exercise, check out `HINTS.md`, but try and solve it without using those first :)

## Introduction

## Bit Manipulation

Java has operators for manipulating the bits of a `byte`, `short`, `int`, `long` or `char`.

### Shift operators

Use `<<` to shift bits to the left and `>>` to shift to the right.

```java
// Shift two places to the left
0b0000_1011 << 2;
// # => 0b0010_1100

// Shift two places to the right
0b0000_1011 >> 2;
// # => 0b0000_0010
```

The `<<` operator always inserts 0s on the right hand side.
However, `>>` inserts the same bit as the left most bit (1 if the number is negative or 0 if positive).

```java
// Shift 2 places to the right preserves the sign
// This is a negative value, whose binary representation is
// 1000_0000_0000_0000_0000_0000_0010_0110
int value = -0x7FFFFFDA;

// Shift two places to the right, preserving the sign bit
value >> 2;
// # => 1110_0000_0000_0000_0000_0000_0000_1001
```

Use `>>>` instead when 0s are to be inserted when shifting to the right.

```java
// Shift two places to the right, inserting 0s on the left
value >>> 2;
// # => 0010_0000_0000_0000_0000_0000_0000_1001
```

### Bitwise Operations

#### Bitwise AND

The bitwise AND (`&`) operator takes two values and performs an AND on each bit.
It compares each bit from the first value with the bit in the same position from the second value.
If they are both 1, then the result's bit is 1.
Otherwise, the result's bit is 0.

```java
0b0110_0101 & 0b0011_1100;
// # => 0b0010_0100
```

#### Bitwise OR

The bitwise OR (`|`) operator takes two values and performs an OR on each bit.
It compares each bit from the first value with the bit in the same position from the second value.
If either bit is 1, the result's bit is 1.
Otherwise, it is 0.

```java
0b0110_0101 | 0b0011_1100;
// # => 0b0111_1101
```

#### Bitwise XOR

The bitwise XOR operator (`^`) performs a bitwise XOR on two values.
Like the bitwise AND and bitwise OR operators, it compares each bit from the first value against the bit in the same position from the second value.
If only one of them is 1, the resulting bit is 1.
Otherwise, it is 0.

```java
0b0110_0101 ^ 0b0011_1100;
// # => 0b0101_1001
```

#### Bitwise NOT(`~`)

Lastly, the bitwise NOT operator (`~`) flips each bit.
Unlike the earlier operators, this is a unary operator, acting only on one value.

```java
~0b0110_0101;
// # => 0b1001_1010
```

## Instructions

Your friend has just sent you a message with an important secret.
Not wanting to make it easy for others to read it, the message was encrypted by performing a series of bit manipulations.
You will need to write the methods to help decrypt the message.

## 1. Shift back the bits

The first step in decrypting the message is to undo the shifting from the encryption process by shifting the bits back to the right.
There will be further steps in the decryption process that assume 0s are inserted from the left hand side.

Implement the `Secrets.shiftBack` method that takes a value and the number of places to shift and peforms the shift.

```java
Secrets.shiftBack(0b1001, 2);
# => 0b0010
```

## 2. Set some bits

Next, there are some bits that need to be set to 1.

Implement the `Secrets.setBits` method that takes a value and a mask and returns the result of setting the bits in value to 1.
A bit from value should be set to 1 where the bit in the mask is also 1.
All other bits should be kept unchanged.

```java
Secrets.setBits(0b0110, 0b0101);
# => 0b0111
```

## 3. Flip specific bits

Some bits are flipped during encryption.
They will need to be flipped back to decrypt the message.

Implement the `Secrets.flipBits` method that takes a value and the mask.
The mask indicates which bits in the value to flip.
If the bit is 1 in mask, the bit is flipped in the value.
All other bits are kept unchanged.

```java
Secrets.flipBits(0b1100, 0b0101);
# => 0b1001
```

## 4. Clear specific bits

Lastly, there are also certain bits that always decrypt to 0.

Implement the `Secrets.clearBits` method that takes a value and a mask.
The bits in the `value` should be set to 0 where the bit in the mask is 1.
All other bits should be kept unchanged.

```java
Secrets.clearBits(0b0110, 0b0101);
# => 0b0010
```

## Source

### Created by

- @kahgoh