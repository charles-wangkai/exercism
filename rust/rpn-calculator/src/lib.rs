#[derive(Debug)]
pub enum CalculatorInput {
    Add,
    Subtract,
    Multiply,
    Divide,
    Value(i32),
}

pub fn evaluate(inputs: &[CalculatorInput]) -> Option<i32> {
    let mut stack = Vec::new();
    for input in inputs {
        if let CalculatorInput::Value(value) = *input {
            stack.push(value);
        } else {
            let operand2 = stack.pop();
            match operand2 {
                Some(operand2) => {
                    let operand1 = stack.pop();
                    match operand1 {
                        Some(operand1) => match *input {
                            CalculatorInput::Add => stack.push(operand1 + operand2),
                            CalculatorInput::Subtract => stack.push(operand1 - operand2),
                            CalculatorInput::Multiply => stack.push(operand1 * operand2),
                            CalculatorInput::Divide => stack.push(operand1 / operand2),
                            CalculatorInput::Value(_) => {}
                        },
                        None => return None,
                    }
                }
                None => return None,
            }
        }
    }

    if stack.len() == 1 {
        Some(stack[0])
    } else {
        None
    }
}
