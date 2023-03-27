use std::collections::HashMap;

pub type Value = i32;
pub type Result = std::result::Result<(), Error>;

pub struct Forth {
    values: Vec<Value>,
    name_to_definitions: HashMap<String, Vec<Vec<Item>>>,
}

#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    DivisionByZero,
    StackUnderflow,
    UnknownWord,
    InvalidWord,
}

#[derive(Clone)]
enum Item {
    Number(Value),
    Add,
    Subtraction,
    Multiplication,
    Division,
    Dup,
    Drop,
    Swap,
    Over,
    Word {
        name: String,
        definition_index: usize,
    },
}

impl Forth {
    pub fn new() -> Self {
        Self {
            values: Vec::new(),
            name_to_definitions: HashMap::new(),
        }
    }

    pub fn stack(&self) -> &[Value] {
        &self.values
    }

    pub fn eval(&mut self, input: &str) -> Result {
        match to_commands(input) {
            Some(commands) => {
                for command in commands {
                    let command = command.to_lowercase();

                    if command.starts_with(": ") {
                        let tokens = tokenize(&command[2..command.len() - 2]);

                        let name = tokens[0];
                        if is_number(name) {
                            return Err(Error::InvalidWord);
                        }

                        let mut definition = Vec::new();
                        for &token in &tokens[1..] {
                            let item = if self.name_to_definitions.contains_key(token) {
                                Item::Word {
                                    name: String::from(token),
                                    definition_index: self.name_to_definitions[token].len() - 1,
                                }
                            } else if is_number(token) {
                                Item::Number(token.parse().unwrap())
                            } else if token == "+" {
                                Item::Add
                            } else if token == "-" {
                                Item::Subtraction
                            } else if token == "*" {
                                Item::Multiplication
                            } else if token == "/" {
                                Item::Division
                            } else if token == "dup" {
                                Item::Dup
                            } else if token == "drop" {
                                Item::Drop
                            } else if token == "swap" {
                                Item::Swap
                            } else if token == "over" {
                                Item::Over
                            } else {
                                panic!()
                            };

                            definition.push(item);
                        }

                        if !self.name_to_definitions.contains_key(name) {
                            self.name_to_definitions
                                .insert(String::from(name), Vec::new());
                        }
                        self.name_to_definitions
                            .get_mut(name)
                            .unwrap()
                            .push(definition);
                    } else {
                        for token in tokenize(&command) {
                            let mut items = Vec::new();
                            if self.name_to_definitions.contains_key(token) {
                                items.extend(
                                    self.name_to_definitions[token]
                                        .last()
                                        .unwrap()
                                        .iter()
                                        .cloned()
                                        .rev(),
                                );
                            } else if is_number(token) {
                                items.push(Item::Number(token.parse().unwrap()));
                            } else if token == "+" {
                                items.push(Item::Add);
                            } else if token == "-" {
                                items.push(Item::Subtraction);
                            } else if token == "*" {
                                items.push(Item::Multiplication);
                            } else if token == "/" {
                                items.push(Item::Division);
                            } else if token == "dup" {
                                items.push(Item::Dup);
                            } else if token == "drop" {
                                items.push(Item::Drop);
                            } else if token == "swap" {
                                items.push(Item::Swap);
                            } else if token == "over" {
                                items.push(Item::Over);
                            } else {
                                return Err(Error::UnknownWord);
                            }

                            while let Some(item) = items.pop() {
                                match item {
                                    Item::Number(x) => self.values.push(x),
                                    Item::Add => {
                                        if self.values.len() < 2 {
                                            return Err(Error::StackUnderflow);
                                        }

                                        let value1 = self.values.pop().unwrap();
                                        let value2 = self.values.pop().unwrap();
                                        self.values.push(value2 + value1);
                                    }
                                    Item::Subtraction => {
                                        if self.values.len() < 2 {
                                            return Err(Error::StackUnderflow);
                                        }

                                        let value1 = self.values.pop().unwrap();
                                        let value2 = self.values.pop().unwrap();
                                        self.values.push(value2 - value1);
                                    }
                                    Item::Multiplication => {
                                        if self.values.len() < 2 {
                                            return Err(Error::StackUnderflow);
                                        }

                                        let value1 = self.values.pop().unwrap();
                                        let value2 = self.values.pop().unwrap();
                                        self.values.push(value2 * value1);
                                    }
                                    Item::Division => {
                                        if self.values.len() < 2 {
                                            return Err(Error::StackUnderflow);
                                        }

                                        let value1 = self.values.pop().unwrap();
                                        if value1 == 0 {
                                            return Err(Error::DivisionByZero);
                                        }

                                        let value2 = self.values.pop().unwrap();
                                        self.values.push(value2 / value1);
                                    }
                                    Item::Dup => {
                                        if self.values.is_empty() {
                                            return Err(Error::StackUnderflow);
                                        }

                                        self.values.push(*self.values.last().unwrap());
                                    }
                                    Item::Drop => {
                                        if self.values.is_empty() {
                                            return Err(Error::StackUnderflow);
                                        }

                                        self.values.pop();
                                    }
                                    Item::Swap => {
                                        if self.values.len() < 2 {
                                            return Err(Error::StackUnderflow);
                                        }

                                        let value1 = self.values.pop().unwrap();
                                        let value2 = self.values.pop().unwrap();
                                        self.values.push(value1);
                                        self.values.push(value2);
                                    }
                                    Item::Over => {
                                        if self.values.len() < 2 {
                                            return Err(Error::StackUnderflow);
                                        }

                                        self.values.push(self.values[self.values.len() - 2]);
                                    }
                                    Item::Word {
                                        name,
                                        definition_index,
                                    } => {
                                        items.extend(
                                            self.name_to_definitions[&name][definition_index]
                                                .iter()
                                                .cloned()
                                                .rev(),
                                        );
                                    }
                                }
                            }
                        }
                    }
                }
            }
            None => {
                return Err(Error::InvalidWord);
            }
        }

        Ok(())
    }
}

fn is_number(s: &str) -> bool {
    s.chars().all(|c| c.is_ascii_digit())
}

fn tokenize(s: &str) -> Vec<&str> {
    s.split_ascii_whitespace().collect()
}

fn to_commands(input: &str) -> Option<Vec<&str>> {
    let mut result = Vec::new();
    let mut rest = input;
    loop {
        match rest.find(':') {
            Some(begin_index) => {
                let command = rest[..begin_index].trim();
                if !command.is_empty() {
                    result.push(command);
                }

                rest = &rest[begin_index..];
                match rest.find(';') {
                    Some(end_index) => {
                        result.push(&rest[..=end_index]);
                        rest = &rest[end_index + 1..];
                    }
                    None => {
                        return None;
                    }
                }
            }
            None => {
                let command = rest.trim();
                if !command.is_empty() {
                    result.push(command);
                }

                break;
            }
        }
    }

    Some(result)
}

impl Default for Forth {
    fn default() -> Self {
        Forth::new()
    }
}
