/* eslint-disable no-unused-vars */
//
// This is only a SKELETON file for the 'Bob' exercise. It's been provided as a
// convenience to get you started writing code faster.
//

export const hey = message => {
  if (/^\s*$/.test(message)) {
    return "Fine. Be that way!";
  }

  const isQuestion = message.trimEnd().endsWith("?");

  if (/[A-Z]/.test(message) && message.toUpperCase() == message) {
    if (isQuestion) {
      return "Calm down, I know what I'm doing!";
    } else {
      return "Whoa, chill out!";
    }
  }

  if (isQuestion) {
    return "Sure.";
  }

  return "Whatever.";
};
