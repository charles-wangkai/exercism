// the PhantomData instances in this file are just to stop compiler complaints
// about missing generics; feel free to remove them

use std::{fmt::Display, ops::Rem};

/// A Matcher is a single rule of fizzbuzz: given a function on T, should
/// a word be substituted in? If yes, which word?
pub struct Matcher<'a, T> {
    matcher: Box<dyn 'a + Fn(T) -> bool>,
    subs: String,
}

impl<'a, T> Matcher<'a, T> {
    pub fn new<'b, F: 'a + Fn(T) -> bool>(matcher: F, subs: &'b str) -> Matcher<'a, T> {
        Self {
            matcher: Box::new(matcher),
            subs: String::from(subs),
        }
    }
}

/// A Fizzy is a set of matchers, which may be applied to an iterator.
///
/// Strictly speaking, it's usually more idiomatic to use `iter.map()` than to
/// consume an iterator with an `apply` method. Given a Fizzy instance, it's
/// pretty straightforward to construct a closure which applies it to all
/// elements of the iterator. However, we're using the `apply` pattern
/// here because it's a simpler interface for students to implement.
///
/// Also, it's a good excuse to try out using impl trait.
pub struct Fizzy<'a, T> {
    matchers: Vec<Matcher<'a, T>>,
}

impl<'a, T: 'a + Copy + Display> Fizzy<'a, T> {
    pub fn new() -> Self {
        Self {
            matchers: Vec::new(),
        }
    }

    // feel free to change the signature to `mut self` if you like
    #[must_use]
    pub fn add_matcher(mut self, matcher: Matcher<'a, T>) -> Self {
        self.matchers.push(matcher);

        self
    }

    /// map this fizzy onto every element of an iterator, returning a new iterator
    pub fn apply<I: 'a + Iterator<Item = T>>(self, iter: I) -> impl 'a + Iterator<Item = String> {
        // unimplemented!() doesn't actually work, here; () is not an Iterator
        // that said, this is probably not the actual implementation you desire
        iter.map(move |e| {
            let mut result = String::new();
            for matcher in &self.matchers {
                if (matcher.matcher)(e) {
                    result.push_str(&matcher.subs);
                }
            }
            if result.is_empty() {
                result.push_str(&e.to_string());
            }

            result
        })
    }
}

/// convenience function: return a Fizzy which applies the standard fizz-buzz rules
pub fn fizz_buzz<'a, T>() -> Fizzy<'a, T>
where
    T: 'a + Copy + Display + Rem + From<u8>,
    <T as Rem>::Output: From<u8> + PartialEq,
{
    Fizzy::new()
        .add_matcher(Matcher::new(|x| x % 3.into() == 0.into(), "fizz"))
        .add_matcher(Matcher::new(|x| x % 5.into() == 0.into(), "buzz"))
}

impl<'a, T: 'a + Copy + Display> Default for Fizzy<'a, T> {
    fn default() -> Self {
        Self::new()
    }
}
