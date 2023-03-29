#[derive(Debug)]
pub struct ChessPosition {
    rank: i32,
    file: i32,
}

#[derive(Debug)]
pub struct Queen {
    position: ChessPosition,
}

impl ChessPosition {
    pub fn new(rank: i32, file: i32) -> Option<Self> {
        if (0..8).contains(&rank) && (0..8).contains(&file) {
            Some(Self { rank, file })
        } else {
            None
        }
    }
}

impl Queen {
    pub fn new(position: ChessPosition) -> Self {
        Self { position }
    }

    pub fn can_attack(&self, other: &Queen) -> bool {
        self.position.rank == other.position.rank
            || self.position.file == other.position.file
            || self.position.rank + self.position.file == other.position.rank + other.position.file
            || self.position.rank - self.position.file == other.position.rank - other.position.file
    }
}
