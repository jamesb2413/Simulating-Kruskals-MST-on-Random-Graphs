import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// connected, undirected graph
public class CUGraph {

    private final int n;
    public List<Edge> elst = new ArrayList<>();
    private Point[] points;

    public static class Edge {

        private final double weight;
        // vertices
        private final int v1id;
        private final int v2id;

        private Edge(int v1, int v2, double wt) {
            weight = wt;
            v1id = v1;
            v2id = v2;
        }

        public int get_i() {
            return v1id;
        }

        public int get_j() {
            return v2id;
        }

        public double get_wt() {
            return weight;
        }

        public int compareTo(Edge e) {
            if (this.get_wt() < e.get_wt())
                return -1;
            else if (e.get_wt() < this.get_wt())
                return 1;
            return 0;
        }
    }

    public CUGraph(int vertices, int dimension) {
        if (vertices < 1 || dimension < 1 || dimension > 4) {
            throw new IllegalArgumentException("Must have positive n and 0 < dim < 5.");
        }
        n = vertices;
        // dim = 1, 2, 3, or 4
        if (dimension == 1) {
            double k = discardK(n, 1);
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    double cur_wt = Math.random();
                    if (cur_wt > k) {
                        continue;
                    }
                    elst.add(new Edge(i, j, cur_wt));
                }
            }
        } else if (dimension == 2) {
            double k = discardK(n, 2);
            points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(Math.random(), Math.random());
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    double ij_wt = distance(points[i].getX(), points[i].getY(), points[j].getX(), points[j].getY());
                    if (ij_wt > k) {
                        continue;
                    }
                    elst.add(new Edge(i, j, ij_wt));
                }
            }
        } else if (dimension == 3) {
            double k = discardK(n, 3);
            points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(Math.random(), Math.random(), Math.random());
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    double ij_wt = distance3(points[i].getX(), points[i].getY(), points[i].getZ(),
                            points[j].getX(), points[j].getY(), points[j].getZ());
                    if (ij_wt > k) {
                        continue;
                    }
                    elst.add(new Edge(i, j, ij_wt));
                }
            }
            // dim 4
        } else {
            double k = discardK(n, 4);
            points = new Point[n];
            for (int i = 0; i < n; i++) {
                points[i] = new Point(Math.random(), Math.random(), Math.random(), Math.random());
            }
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    double ij_wt = distance4(points[i].getX(), points[i].getY(), points[i].getZ(), points[i].getW(),
                            points[j].getX(), points[j].getY(), points[j].getZ(), points[j].getW());
                    if (ij_wt > k) {
                        continue;
                    }
                    elst.add(new Edge(i, j, ij_wt));
                }
            }
        }
        elst.sort(new edgeCompare());
    }

    // Pruning: Discards edges that are extremely unlikely to be chosen in MST 
    // k(n) function to determine unlikely weight threshold estimated using small values of n for each dimension
    private double discardK(int n, int dim) {
        if (dim == 1) {
            return 2.7237 * Math.pow(n, -0.783);
        } else if (dim == 2) {
            return 1.7985 * Math.pow(n, -0.444);
        } else if (dim == 3) {
            return 1.6406 * Math.pow(n, -0.304);
        } else {
            return 1.6156 * Math.pow(n, -0.24);
        }
    }

    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    private double distance3(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt((z2 - z1) * (z2 - z1) + (y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    private double distance4(double x1, double y1, double z1, double w1, double x2, double y2, double z2, double w2) {
        return Math.sqrt((w2 - w1) * (w2 - w1) + (z2 - z1) * (z2 - z1) + (y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public int getSize() {
        return n;
    }

    private static class edgeCompare implements Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.compareTo(e2);
        }
    }

    // string representation of graph adjacency list
    public String toString() {
        StringBuilder s = new StringBuilder();
        if (dim == 1) {
            for (int i = 0; i < n - 1; i++) {
                Edge e = adjLst[i];
                s.append("Edge (").append(e.v1id).append(",").append(e.v2id).append(
                        "): weight = ").append(e.weight).append("\n");
                while (e.child != null) {
                    e = e.child;
                    s.append("i = ").append(i).append(". Edge (").append(e.v1id).append(",").append(e.v2id).append(
                            "): weight = ").append(e.weight).append("\n");
                }
            }
            s.append("sorted: [");
            List<Edge> eSort = this.sortedE();
            int e_n = eSort.size();
            for (int i = 0; i < e_n - 1; i++) {
                s.append(eSort.get(i).get_wt()).append(", ");
                if (i != 0 && i % 5 == 0) {
                    s.append("\n         ");
                }
            }
            s.append(eSort.get(e_n - 1).get_wt()).append("]\n");
        } else if (dim == 2) {
            s.append("Vertices: \n");
            for (int i = 0; i < n; i++) {
                double x = points[i].getX();
                double y = points[i].getY();
                s.append(i).append(": (").append(x).append(", ").append(y).append(")\n");
            }
            for (int i = 0; i < n - 1; i++) {
                Edge e = adjLst[i];
                s.append("Edge (").append(e.v1id).append(",").append(e.v2id).append(
                        "): weight = ").append(e.weight).append("\n");
                while (e.child != null) {
                    e = e.child;
                    s.append("Edge (").append(e.v1id).append(",").append(e.v2id).append(
                            "): weight = ").append(e.weight).append("\n");
                }
            }
            s.append("sorted: [");
            List<Edge> eSort = this.sortedE();
            int e_n = eSort.size();
            for (int i = 0; i < e_n - 1; i++) {
                s.append(eSort.get(i).get_wt()).append(", ");
                if (i != 0 && i % 5 == 0) {
                    s.append("\n         ");
                }
            }
            s.append(eSort.get(e_n - 1).get_wt()).append("]\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        /* CUGraph one = new CUGraph(8, 1);
        // System.out.print(one);
        CUGraph two = new CUGraph(8, 2);
        // System.out.print(two);
        CUGraph three = new CUGraph(8, 3);
        // System.out.print(three); */
        /*CUGraph four = new CUGraph(8, 4);
        System.out.print(four);*/
    }

}
