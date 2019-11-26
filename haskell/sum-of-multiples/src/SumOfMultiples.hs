module SumOfMultiples
  ( sumOfMultiples
  ) where

import qualified Data.Set as Set

sumOfMultiples :: [Integer] -> Integer -> Integer
sumOfMultiples factors limit = sum $ Set.unions $ map multiplesOf factors
  where
    multiplesOf n
      | n == 0 = Set.empty
      | otherwise = Set.fromList [n,n * 2 .. limit - 1]
