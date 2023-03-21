#[macro_export]
macro_rules! hashmap {
    () => {
        {
            ::std::collections::HashMap::new()
        }
    };
    ($($k:expr => $v:expr),+ $(,)?) => {
        {
            let mut hm = ::std::collections::HashMap::new();
            $(
                hm.insert($k, $v);
            )*
            hm
        }
    };
}
