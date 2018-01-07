module CollatzConjecture (collatz) where

collatz :: Integer -> Maybe Integer
collatz number
    | number <= 0 = Nothing
    | otherwise = Just (f number 0)
                  where f number step
                            | number == 1 = step
                            | even number = f (number `div` 2) (step + 1)
                            | otherwise = f (3 * number + 1) (step + 1)
