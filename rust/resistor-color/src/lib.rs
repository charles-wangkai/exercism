use enum_iterator::Sequence;
use int_enum::IntEnum;
use std::fmt::Debug;

#[repr(u32)]
#[derive(Clone, Copy, Debug, PartialEq, Eq, IntEnum, Sequence)]
pub enum ResistorColor {
    Black = 0,
    Brown = 1,
    Red = 2,
    Orange = 3,
    Yellow = 4,
    Green = 5,
    Blue = 6,
    Violet = 7,
    Grey = 8,
    White = 9,
}

pub fn color_to_value(color: ResistorColor) -> u32 {
    color.int_value()
}

pub fn value_to_color_string(value: u32) -> String {
    match ResistorColor::from_int(value) {
        Ok(color) => format!("{color:?}"),
        Err(_) => String::from("value out of range"),
    }
}

pub fn colors() -> Vec<ResistorColor> {
    enum_iterator::all().collect()
}
