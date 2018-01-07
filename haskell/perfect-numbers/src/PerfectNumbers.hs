module PerfectNumbers (classify, Classification(..)) where

data Classification = Deficient | Perfect | Abundant deriving (Eq, Show)

classify :: Int -> Maybe Classification
classify number = if number <= 0 then Nothing
                  else let aliquotSum = computeAliquotSum number
                       in if aliquotSum == number then Just Perfect
                          else if aliquotSum > number then Just Abundant
                          else Just Deficient

computeAliquotSum :: Int -> Int
computeAliquotSum n
    | n == 1 = 0
    | otherwise = 1 + (sum $ map (\i -> if i * i == n then i else i + n `div` i) $ filter (\i -> n `mod` i == 0) $ takeWhile (\i -> i * i <= n) [2..])
