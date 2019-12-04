module Grains
  ( square
  , total
  ) where

square :: Integer -> Maybe Integer
square n
  | n >= 1 && n <= 64 = Just (2 ^ (n - 1))
  | otherwise = Nothing

total :: Integer
total = 2 ^ (64 :: Int) - 1
