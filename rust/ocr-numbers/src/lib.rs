// The code below is a stub. Just enough to satisfy the compiler.
// In order to pass the tests you can add-to or change any of this code.

#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    InvalidRowCount(usize),
    InvalidColumnCount(usize),
}

const IMAGES: &[&str] = &[
    "
 _ 
| |
|_|
   ",
    "
   
  |
  |
   ",
    "
 _ 
 _|
|_ 
   ",
    "
 _ 
 _|
 _|
   ",
    "
   
|_|
  |
   ",
    "
 _ 
|_ 
 _|
   ",
    "
 _ 
|_ 
|_|
   ",
    "
 _ 
  |
  |
   ",
    "
 _ 
|_|
|_|
   ",
    "
 _ 
|_|
 _|
   ",
];

pub fn convert(input: &str) -> Result<String, Error> {
    let rows: Vec<_> = input.split('\n').collect();

    let row_num = rows.len();
    if row_num % 4 != 0 {
        return Err(Error::InvalidRowCount(row_num));
    }

    let col_num = rows[0].len();
    if col_num % 3 != 0 {
        return Err(Error::InvalidColumnCount(col_num));
    }

    Ok((0..row_num)
        .step_by(4)
        .map(|r| {
            (0..col_num)
                .step_by(3)
                .map(|c| recognize(&rows, r, c))
                .collect::<String>()
        })
        .collect::<Vec<_>>()
        .join(","))
}

fn recognize(rows: &[&str], begin_r: usize, begin_c: usize) -> String {
    IMAGES
        .iter()
        .enumerate()
        .find(|(_, &image)| {
            (begin_r..begin_r + 4)
                .map(|r| &rows[r][begin_c..begin_c + 3])
                .collect::<Vec<_>>()
                .join("\n")
                == image[1..]
        })
        .map_or(String::from("?"), |(i, _)| i.to_string())
}
