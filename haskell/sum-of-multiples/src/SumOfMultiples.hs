module SumOfMultiples
  ( sumOfMultiples
  ) where

import qualified Data.Set as Set

sumOfMultiples :: [Integer] -> Integer -> Integer
sumOfMultiples factors limit = sum . Set.unions . map multiplesOf $ factors
  where
    multiplesOf 0 = Set.singleton 0
    multiplesOf n = Set.fromList [0,n .. limit - 1]
