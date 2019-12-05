//
// This is only a SKELETON file for the 'Secret Handshake' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

const SIGNALS = ["wink", "double blink", "close your eyes", "jump"];

export const secretHandshake = n => {
  if (typeof n !== "number") {
    throw new Error("Handshake must be a number");
  }

  const result = [];
  let remain = n;
  for (const signal of SIGNALS) {
    if (remain & 1) {
      result.push(signal);
    }

    remain >>= 1;
  }
  if (remain & 1) {
    result.reverse();
  }

  return result;
};
