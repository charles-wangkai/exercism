module Pangram (isPangram) where

import Data.Char
import qualified Data.Set as Set

isPangram :: String -> Bool
isPangram = (== 26) . Set.size . Set.fromList . filter isAlpha . map toLower