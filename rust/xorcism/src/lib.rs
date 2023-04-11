use std::{
    borrow::Borrow,
    io::{Read, Write},
};

/// A munger which XORs a key with some data
#[derive(Clone)]
pub struct Xorcism<'a> {
    key: &'a [u8],
    index: usize,
}

impl<'a> Xorcism<'a> {
    /// Create a new Xorcism munger from a key
    ///
    /// Should accept anything which has a cheap conversion to a byte slice.
    pub fn new<Key: ?Sized + AsRef<[u8]>>(key: &'a Key) -> Self {
        Self {
            key: key.as_ref(),
            index: 0,
        }
    }

    /// XOR each byte of the input buffer with a byte from the key.
    ///
    /// Note that this is stateful: repeated calls are likely to produce different results,
    /// even with identical inputs.
    pub fn munge_in_place(&mut self, data: &mut [u8]) {
        for x in data.iter_mut() {
            *x ^= self.key[self.index];
            self.index = (self.index + 1) % self.key.len();
        }
    }

    /// XOR each byte of the data with a byte from the key.
    ///
    /// Note that this is stateful: repeated calls are likely to produce different results,
    /// even with identical inputs.
    ///
    /// Should accept anything which has a cheap conversion to a byte iterator.
    /// Shouldn't matter whether the byte iterator's values are owned or borrowed.
    pub fn munge<'b, I, Data>(&'b mut self, data: Data) -> impl 'b + Iterator<Item = u8>
    where
        Data: 'b + IntoIterator<Item = I>,
        I: Borrow<u8>,
    {
        data.into_iter().map(|x| {
            let result = *x.borrow() ^ self.key[self.index];
            self.index = (self.index + 1) % self.key.len();

            result
        })
    }

    pub fn reader(self, reader: impl 'a + Read) -> impl 'a + Read {
        XorcismIO {
            xorcism: self,
            io: reader,
        }
    }

    pub fn writer(self, writer: impl 'a + Write) -> impl 'a + Write {
        XorcismIO {
            xorcism: self,
            io: writer,
        }
    }
}

struct XorcismIO<'a, IO> {
    xorcism: Xorcism<'a>,
    io: IO,
}

impl<IO: Read> Read for XorcismIO<'_, IO> {
    fn read(&mut self, buf: &mut [u8]) -> std::io::Result<usize> {
        match self.io.read(buf) {
            Ok(bytes_read) => {
                self.xorcism.munge_in_place(buf);

                Ok(bytes_read)
            }
            Err(x) => Err(x),
        }
    }
}

impl<IO: Write> Write for XorcismIO<'_, IO> {
    fn write(&mut self, buf: &[u8]) -> std::io::Result<usize> {
        self.io.write(&self.xorcism.munge(buf).collect::<Vec<_>>())
    }

    fn flush(&mut self) -> std::io::Result<()> {
        self.io.flush()
    }
}
