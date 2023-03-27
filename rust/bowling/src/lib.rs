#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    NotEnoughPinsLeft,
    GameComplete,
}

pub struct BowlingGame {
    rolls: Vec<u16>,
}

struct Frame {
    roll1: u16,
    roll2: u16,
}

enum FrameType {
    Strike,
    Spare,
    Open,
}

impl Frame {
    fn get_frame_type(&self) -> FrameType {
        if self.roll1 == 10 {
            FrameType::Strike
        } else if self.roll1 + self.roll2 == 10 {
            FrameType::Spare
        } else {
            FrameType::Open
        }
    }
}

impl BowlingGame {
    pub fn new() -> Self {
        Self { rolls: Vec::new() }
    }

    pub fn roll(&mut self, pins: u16) -> Result<(), Error> {
        self.rolls.push(pins);

        match self.build_frames() {
            Ok(_) => Ok(()),
            Err(error) => Err(error),
        }
    }

    fn build_frames(&self) -> Result<Option<Vec<Frame>>, Error> {
        let mut frames = Vec::new();
        let mut roll_index = 0;
        for _ in 0..10 {
            match self.read_frame(&mut roll_index) {
                Ok(Some(frame)) => frames.push(frame),
                Ok(None) => return Ok(None),
                Err(error) => return Err(error),
            }
        }

        match frames[9].get_frame_type() {
            FrameType::Strike => match self.read_roll(&mut roll_index) {
                Ok(Some(roll1)) => match self.read_roll(&mut roll_index) {
                    Ok(Some(roll2)) => {
                        if roll1 != 10 && roll1 + roll2 > 10 {
                            return Err(Error::NotEnoughPinsLeft);
                        }

                        frames.push(Frame { roll1, roll2 });
                    }
                    Ok(None) => return Ok(None),
                    Err(error) => return Err(error),
                },
                Ok(None) => return Ok(None),
                Err(error) => return Err(error),
            },
            FrameType::Spare => match self.read_roll(&mut roll_index) {
                Ok(Some(roll1)) => frames.push(Frame { roll1, roll2: 0 }),
                Ok(None) => return Ok(None),
                Err(error) => return Err(error),
            },
            _ => (),
        }

        if roll_index != self.rolls.len() {
            return Err(Error::GameComplete);
        }

        Ok(Some(frames))
    }

    fn read_frame(&self, roll_index: &mut usize) -> Result<Option<Frame>, Error> {
        match self.read_roll(roll_index) {
            Ok(Some(roll1)) => {
                if roll1 == 10 {
                    Ok(Some(Frame {
                        roll1: 10,
                        roll2: 0,
                    }))
                } else {
                    match self.read_roll(roll_index) {
                        Ok(Some(roll2)) => {
                            if roll1 + roll2 > 10 {
                                return Err(Error::NotEnoughPinsLeft);
                            }

                            Ok(Some(Frame { roll1, roll2 }))
                        }
                        Ok(None) => Ok(None),
                        Err(error) => Err(error),
                    }
                }
            }
            Ok(None) => Ok(None),
            Err(error) => Err(error),
        }
    }

    fn read_roll(&self, roll_index: &mut usize) -> Result<Option<u16>, Error> {
        if *roll_index == self.rolls.len() {
            return Ok(None);
        }

        let roll = self.rolls[*roll_index];
        *roll_index += 1;

        if roll > 10 {
            return Err(Error::NotEnoughPinsLeft);
        }

        Ok(Some(roll))
    }

    pub fn score(&self) -> Option<u16> {
        match self.build_frames().unwrap() {
            Some(frames) => {
                let mut result = 0;
                for i in 0..9 {
                    result += frames[i].roll1 + frames[i].roll2;

                    match frames[i].get_frame_type() {
                        FrameType::Strike => {
                            let next_roll = frames[i + 1].roll1;
                            let next_next_roll = if next_roll == 10 {
                                frames[i + 2].roll1
                            } else {
                                frames[i + 1].roll2
                            };

                            result += next_roll + next_next_roll;
                        }
                        FrameType::Spare => result += frames[i + 1].roll1,
                        _ => (),
                    }
                }
                result += (9..frames.len())
                    .map(|i| frames[i].roll1 + frames[i].roll2)
                    .sum::<u16>();

                Some(result)
            }
            None => None,
        }
    }
}

impl Default for BowlingGame {
    fn default() -> Self {
        Self::new()
    }
}
