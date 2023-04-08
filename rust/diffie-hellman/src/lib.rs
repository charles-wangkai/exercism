use mod_exp::mod_exp;
use rand::Rng;

pub fn private_key(p: u64) -> u64 {
    rand::thread_rng().gen_range(2..p)
}

pub fn public_key(p: u64, g: u64, a: u64) -> u64 {
    mod_exp(g as u128, a as u128, p as u128) as u64
}

pub fn secret(p: u64, b_pub: u64, a: u64) -> u64 {
    mod_exp(b_pub as u128, a as u128, p as u128) as u64
}
