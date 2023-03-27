pub mod graph {
    use std::collections::HashMap;

    use self::graph_items::{edge::Edge, node::Node};

    pub mod graph_items {
        pub mod edge {
            use std::collections::HashMap;

            #[derive(Debug, PartialEq, Eq, Clone)]
            pub struct Edge {
                from: String,
                to: String,
                attrs: HashMap<String, String>,
            }

            impl Edge {
                pub fn new(from: &str, to: &str) -> Self {
                    Self {
                        from: String::from(from),
                        to: String::from(to),
                        attrs: HashMap::new(),
                    }
                }

                pub fn with_attrs(mut self, attrs: &[(&str, &str)]) -> Self {
                    self.attrs = HashMap::from_iter(
                        attrs
                            .iter()
                            .map(|(key, value)| (String::from(*key), String::from(*value))),
                    );

                    self
                }

                pub fn attr(&self, key: &str) -> Option<&str> {
                    self.attrs
                        .iter()
                        .find(|attr| attr.0 == key)
                        .map(|attr| &**attr.1)
                }
            }
        }

        pub mod node {
            use std::collections::HashMap;

            #[derive(Debug, PartialEq, Eq, Clone)]
            pub struct Node {
                pub name: String,
                pub attrs: HashMap<String, String>,
            }

            impl Node {
                pub fn new(name: &str) -> Self {
                    Self {
                        name: String::from(name),
                        attrs: HashMap::new(),
                    }
                }

                pub fn with_attrs(mut self, attrs: &[(&str, &str)]) -> Self {
                    self.attrs = HashMap::from_iter(
                        attrs
                            .iter()
                            .map(|(key, value)| (String::from(*key), String::from(*value))),
                    );

                    self
                }

                pub fn attr(&self, key: &str) -> Option<&str> {
                    self.attrs
                        .iter()
                        .find(|attr| attr.0 == key)
                        .map(|attr| &**attr.1)
                }
            }
        }
    }

    pub struct Graph {
        pub nodes: Vec<Node>,
        pub edges: Vec<Edge>,
        pub attrs: HashMap<String, String>,
    }

    impl Graph {
        pub fn new() -> Self {
            Self {
                nodes: Vec::new(),
                edges: Vec::new(),
                attrs: HashMap::new(),
            }
        }

        pub fn with_nodes(mut self, nodes: &[Node]) -> Self {
            self.nodes = nodes.to_vec();

            self
        }

        pub fn with_edges(mut self, edges: &[Edge]) -> Self {
            self.edges = edges.to_vec();

            self
        }

        pub fn with_attrs(mut self, attrs: &[(&str, &str)]) -> Self {
            self.attrs = HashMap::from_iter(
                attrs
                    .iter()
                    .map(|(key, value)| (String::from(*key), String::from(*value))),
            );

            self
        }

        pub fn node(&self, name: &str) -> Option<Node> {
            self.nodes.iter().cloned().find(|node| node.name == name)
        }
    }

    impl Default for Graph {
        fn default() -> Self {
            Self::new()
        }
    }
}
