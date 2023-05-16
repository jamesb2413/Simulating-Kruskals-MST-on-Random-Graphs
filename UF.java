public class UF {

    private final Node[] vertices;

    public UF(CUGraph G) {
        int n = G.getSize();
        vertices = new Node[n];
        for (int i = 0; i < n; i++) {
            vertices[i] = makeSet(i);
        }
    }

    public static class Node {
        private Node p;  // parent
        private int id;
        private int rank;
    }

    private Node makeSet(int i) {
        Node x = new Node();
        x.id = i;
        x.p = x;
        x.rank = 0;
        return x;
    }

    public Node find(int i) {
        Node x = vertices[i];
        if (x == x.p) {
            return x;
        }
        x.p = find(x.p.id);
        return x.p;
    }

    private void link(Node x, Node y) {
        if (x.rank > y.rank) {
            link(y, x);
            return;
        }
        if (x.rank == y.rank) {
            y.rank += 1;
        }
        x.p = y;
    }

    public void union(int i, int j) {
        link(find(i), find(j));
    }
}
