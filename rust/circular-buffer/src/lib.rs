pub struct CircularBuffer<T> {
    buffer: Vec<Option<T>>,
    head_index: usize,
    tail_index: usize,
    length: usize,
}

#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    EmptyBuffer,
    FullBuffer,
}

impl<T: Clone> CircularBuffer<T> {
    pub fn new(capacity: usize) -> Self {
        Self {
            buffer: vec![None; capacity],
            head_index: 0,
            tail_index: 0,
            length: 0,
        }
    }

    pub fn write(&mut self, element: T) -> Result<(), Error> {
        if self.is_full() {
            return Err(Error::FullBuffer);
        }

        self.buffer[self.tail_index] = Some(element);
        self.tail_index = (self.tail_index + 1) % self.buffer.len();
        self.length += 1;

        Ok(())
    }

    pub fn read(&mut self) -> Result<T, Error> {
        if self.is_empty() {
            return Err(Error::EmptyBuffer);
        }

        let result = self.buffer[self.head_index].take().unwrap();
        self.head_index = (self.head_index + 1) % self.buffer.len();
        self.length -= 1;

        Ok(result)
    }

    pub fn clear(&mut self) {
        while !self.is_empty() {
            self.read().unwrap();
        }
    }

    pub fn overwrite(&mut self, element: T) {
        if self.is_full() {
            self.read().unwrap();
        }
        self.write(element).unwrap();
    }

    fn is_empty(&self) -> bool {
        self.length == 0
    }

    fn is_full(&self) -> bool {
        self.length != 0 && self.head_index == self.tail_index
    }
}
