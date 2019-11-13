module RunLength
  ( decode
  , encode
  ) where

import Data.Char

decode :: String -> String
decode encodedText = f "" encodedText
  where
    f result s =
      if null s
        then result
        else if isSpace (head s)
               then f (result ++ [head s]) (tail s)
               else case reads s of
                      [] -> f (result ++ [head s]) (tail s)
                      [(count, ch:remain)] ->
                        f (result ++ replicate count ch) remain

encode :: String -> String
encode text =
  let (result, prev, count) = foldl f ("", '\0', -1) text
   in result ++ represent prev count
  where
    f (result, prev, count) ch
      | ch == prev = (result, ch, count + 1)
      | otherwise = (result ++ represent prev count, ch, 1)
    represent ch count
      | count <= 0 = ""
      | count == 1 = [ch]
      | otherwise = show count ++ [ch]
