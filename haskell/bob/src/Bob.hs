module Bob
  ( responseFor
  ) where

import Data.Char
import Data.String.Utils

responseFor :: String -> String
responseFor phrase
  | all isSpace phrase = "Fine. Be that way!"
  | isYell && isAsk = "Calm down, I know what I'm doing!"
  | isYell = "Whoa, chill out!"
  | isAsk = "Sure."
  | otherwise = "Whatever."
  where
    isYell =
      let content = filter isAlpha phrase
       in not (null content) && all isUpper content
    isAsk = endswith "?" $ rstrip phrase
