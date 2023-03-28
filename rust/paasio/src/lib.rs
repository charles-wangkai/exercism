use std::io::{Read, Result, Write};

pub struct ReadStats<R> {
    wrapped: R,
    byte_num: usize,
    read_num: usize,
}

impl<R: Read> ReadStats<R> {
    pub fn new(wrapped: R) -> Self {
        Self {
            wrapped,
            byte_num: 0,
            read_num: 0,
        }
    }

    pub fn get_ref(&self) -> &R {
        &self.wrapped
    }

    pub fn bytes_through(&self) -> usize {
        self.byte_num
    }

    pub fn reads(&self) -> usize {
        self.read_num
    }
}

impl<R: Read> Read for ReadStats<R> {
    fn read(&mut self, buf: &mut [u8]) -> Result<usize> {
        let result = self.wrapped.read(buf);

        if let Ok(bytes_read) = result {
            self.byte_num += bytes_read;
        }
        self.read_num += 1;

        result
    }
}

pub struct WriteStats<W> {
    wrapped: W,
    byte_num: usize,
    write_num: usize,
}

impl<W: Write> WriteStats<W> {
    pub fn new(wrapped: W) -> Self {
        Self {
            wrapped,
            byte_num: 0,
            write_num: 0,
        }
    }

    pub fn get_ref(&self) -> &W {
        &self.wrapped
    }

    pub fn bytes_through(&self) -> usize {
        self.byte_num
    }

    pub fn writes(&self) -> usize {
        self.write_num
    }
}

impl<W: Write> Write for WriteStats<W> {
    fn write(&mut self, buf: &[u8]) -> Result<usize> {
        let result = self.wrapped.write(buf);

        if let Ok(bytes_write) = result {
            self.byte_num += bytes_write;
        }
        self.write_num += 1;

        result
    }

    fn flush(&mut self) -> Result<()> {
        self.wrapped.flush()
    }
}
