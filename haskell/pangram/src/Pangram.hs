module Pangram (isPangram) where

import Data.Char
import qualified Data.Set as Set

isPangram :: String -> Bool
isPangram = (== 26) . Set.size . Set.fromList . filter (\c -> c >= 'a' && c <= 'z') . map toLower