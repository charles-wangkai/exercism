import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Graph {
  private Map<String, String> attributes;
  private Collection<Node> nodes = new ArrayList<>();
  private Collection<Edge> edges = new ArrayList<>();

  public Graph() {
    this(Map.of());
  }

  public Graph(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  public Collection<Node> getNodes() {
    return nodes;
  }

  public Collection<Edge> getEdges() {
    return edges;
  }

  public Graph node(String name) {
    nodes.add(new Node(name));

    return this;
  }

  public Graph node(String name, Map<String, String> attributes) {
    nodes.add(new Node(name, attributes));

    return this;
  }

  public Graph edge(String start, String end) {
    edges.add(new Edge(start, end));

    return this;
  }

  public Graph edge(String start, String end, Map<String, String> attributes) {
    edges.add(new Edge(start, end, attributes));

    return this;
  }

  public Map<String, String> getAttributes() {
    return attributes;
  }
}
