//
// This is only a SKELETON file for the 'Resistor Color Duo' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const COLOR_TO_DIGIT = {
  black: 0,
  brown: 1,
  red: 2,
  orange: 3,
  yellow: 4,
  green: 5,
  blue: 6,
  violet: 7,
  grey: 8,
  white: 9
};

export const value = colors => {
  return COLOR_TO_DIGIT[colors[0]] * 10 + COLOR_TO_DIGIT[colors[1]];
};
