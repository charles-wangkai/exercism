//
// This is only a SKELETON file for the 'Secret Handshake' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const SIGNALS = ["wink", "double blink", "close your eyes", "jump"];

export const secretHandshake = n => {
  if (typeof n !== "number") {
    throw new Error("Handshake must be a number");
  }

  const result = SIGNALS.filter((signal, index) => n & (1 << index));

  if (n & (1 << SIGNALS.length)) {
    result.reverse();
  }

  return result;
};
