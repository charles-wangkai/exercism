#[derive(PartialEq, Eq, Debug)]
pub enum Bucket {
    One,
    Two,
}

/// A struct to hold your results in.
#[derive(PartialEq, Eq, Debug)]
pub struct BucketStats {
    /// The total number of "moves" it should take to reach the desired number of liters, including
    /// the first fill.
    pub moves: u8,
    /// Which bucket should end up with the desired number of liters? (Either "one" or "two")
    pub goal_bucket: Bucket,
    /// How many liters are left in the other bucket?
    pub other_bucket: u8,
}

/// Solve the bucket problem
pub fn solve(
    capacity_1: u8,
    capacity_2: u8,
    goal: u8,
    start_bucket: &Bucket,
) -> Option<BucketStats> {
    let (start_capacity, other_capacity) = match start_bucket {
        Bucket::One => (capacity_1, capacity_2),
        Bucket::Two => (capacity_2, capacity_1),
    };

    let mut moves = 0;
    let mut start_liter = 0;
    let mut other_liter = 0;
    loop {
        if start_liter == goal {
            return Some(BucketStats {
                moves,
                goal_bucket: match start_bucket {
                    Bucket::One => Bucket::One,
                    Bucket::Two => Bucket::Two,
                },
                other_bucket: other_liter,
            });
        }
        if other_liter == goal {
            return Some(BucketStats {
                moves,
                goal_bucket: match start_bucket {
                    Bucket::One => Bucket::Two,
                    Bucket::Two => Bucket::One,
                },
                other_bucket: start_liter,
            });
        }
        if start_liter == 0 && other_liter == other_capacity {
            return None;
        }

        if start_liter == 0 {
            start_liter = start_capacity;
        } else if other_capacity == goal {
            other_liter = other_capacity;
        } else if other_liter == other_capacity {
            other_liter = 0;
        } else {
            let transfer = start_liter.min(other_capacity - other_liter);
            start_liter -= transfer;
            other_liter += transfer;
        }

        moves += 1;
    }
}
