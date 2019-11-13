module DNA
  ( toRNA
  ) where

import qualified Data.Map as Map

toRNA :: String -> Either Char String
toRNA xs = foldl f (Right "") xs
  where
    f result x =
      case result of
        Left _ -> result
        Right v ->
          case dnaToRna Map.!? x of
            Nothing -> Left x
            Just r -> Right (v ++ [r])
    dnaToRna = Map.fromList [('G', 'C'), ('C', 'G'), ('T', 'A'), ('A', 'U')]
