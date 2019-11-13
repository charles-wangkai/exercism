module RailFenceCipher
  ( encode
  , decode
  ) where

encode :: Int -> String -> String
encode rails message =
  let pattern = fencePattern rails $ length message
   in concat [[message !! col | col <- line] | line <- pattern]

decode :: Int -> String -> String
decode rails encodedMessage =
  let pattern = fencePattern rails $ length encodedMessage
      (result, _) =
        foldl f (replicate (length encodedMessage) undefined, 0) pattern
   in result
  where
    f (result, index) line = foldl f1 (result, index) line
      where
        f1 (result, index) col =
          let (xs, _:ys) = splitAt col result
           in (xs ++ (encodedMessage !! index) : ys, index + 1)

fencePattern :: Int -> Int -> [[Int]]
fencePattern rails messageSize =
  let (pattern, _, _) =
        foldl f (replicate rails [], 0, 1) [0 .. messageSize - 1]
   in pattern
  where
    f (pattern, row, rowOffset) col
      | row < 0 || row >= length pattern =
        f (pattern, row - rowOffset * 2, -rowOffset) col
      | otherwise =
        ( [ (pattern !! r) ++
          if r == row
            then [col]
            else []
          | r <- [0 .. rails - 1]
          ]
        , row + rowOffset
        , rowOffset)
