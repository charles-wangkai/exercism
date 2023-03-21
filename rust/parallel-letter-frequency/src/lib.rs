use std::{collections::HashMap, sync::mpsc, thread};

pub fn frequency(input: &[&str], worker_count: usize) -> HashMap<char, usize> {
    let unit = (input.len() + worker_count - 1) / worker_count;

    let (sender, receiver) = mpsc::channel();

    let mut senders: Vec<_> = (0..worker_count - 1).map(|_| sender.clone()).collect();
    senders.push(sender);

    for i in 0..worker_count {
        let sender = senders.pop().unwrap();
        let texts: Vec<String> = (unit * i..(unit * (i + 1)).min(input.len()))
            .map(|j| String::from(input[j]))
            .collect();

        thread::spawn(move || {
            let mut worker_letter_to_count = HashMap::new();
            for text in texts {
                for c in text.to_lowercase().chars() {
                    if c.is_alphabetic() {
                        worker_letter_to_count
                            .entry(c)
                            .and_modify(|count| *count += 1)
                            .or_insert(1);
                    }
                }
            }

            sender.send(worker_letter_to_count).unwrap();
        });
    }

    let mut letter_to_count = HashMap::new();
    for worker_letter_to_count in receiver {
        for (letter, c) in worker_letter_to_count {
            letter_to_count
                .entry(letter)
                .and_modify(|count| *count += c)
                .or_insert(c);
        }
    }

    letter_to_count
}
