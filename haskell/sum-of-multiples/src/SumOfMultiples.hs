module SumOfMultiples
  ( sumOfMultiples
  ) where

import qualified Data.Set as Set

sumOfMultiples :: [Integer] -> Integer -> Integer
sumOfMultiples factors limit =
  sum $
  Set.fromList
    [ multiple
    | factor <- factors
    , factor /= 0
    , multiple <- [factor,factor * 2 .. limit - 1]
    ]
