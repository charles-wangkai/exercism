module Grains
  ( square
  , total
  ) where

import Data.Bits

square :: Integer -> Maybe Integer
square n
  | n >= 1 && n <= 64 = Just (shift 1 $ (fromIntegral n) - 1)
  | otherwise = Nothing

total :: Integer
total = sum [x | Just x <- map square [1 .. 64]]
