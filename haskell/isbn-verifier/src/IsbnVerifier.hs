module IsbnVerifier (isbn) where

import Data.Char
import Data.String.Utils

isbn :: String -> Bool
isbn str = let s = replace "-" "" str
           in length s == 10
            && all isDigit (init s)
            && (s !! 9) `elem` (['0'..'9'] ++ "X")
            && (sum [(parseInt (s !! i)) * (10 - i) | i <- [0..9]]) `mod` 11 == 0

parseInt :: Char -> Int
parseInt ch = if ch == 'X' then 10 else ord(ch) - ord('0')