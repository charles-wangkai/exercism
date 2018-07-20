module CollatzConjecture (collatz) where

collatz :: Integer -> Maybe Integer
collatz number
    | number <= 0 = Nothing
    | otherwise = Just (toInteger $ length $ takeWhile (/= 1) $ iterate f number)
    where f x = if even x then (x `div` 2) else (3 * x + 1)
