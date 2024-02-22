public class Node implements Comparable<Node> {
    String state;
    int cost;
    Node parent;

    public Node(String state, int cost, Node parent) {
        this.state = state;
        this.cost = cost;
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}
