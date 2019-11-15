module DNA
  ( nucleotideCounts
  , Nucleotide(..)
  ) where

import Data.Map (Map)
import qualified Data.Map as Map

data Nucleotide
  = A
  | C
  | G
  | T
  deriving (Eq, Ord, Show)

nucleotideCounts :: String -> Either String (Map Nucleotide Int)
nucleotideCounts xs =
  foldl f (Right $ Map.fromList [(A, 0), (C, 0), (G, 0), (T, 0)]) xs
  where
    f result x =
      case result of
        Left _ -> result
        Right counter ->
          case charToNucleotide x of
            Just k -> Right $ Map.insertWith (+) k 1 counter
            Nothing -> Left "Invalid character!"

charToNucleotide :: Char -> Maybe Nucleotide
charToNucleotide 'A' = Just A
charToNucleotide 'C' = Just C
charToNucleotide 'G' = Just G
charToNucleotide 'T' = Just T
charToNucleotide _ = Nothing
