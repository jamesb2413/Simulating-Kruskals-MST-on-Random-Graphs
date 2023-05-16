public class MST {

    /*public static double primWeight(CUGraph G) {
        int n = G.getSize();
        double inf = Double.POSITIVE_INFINITY;
        // Always start at vertex 0
        Point s = new Point(0.0, 0.0);
        // keeps track of edge weights from current vertex
        double[] minE = new double[n];
        // keeps track of tree order
        // int[] prev = new int[n];
        // keeps track of total weight
        double weight = 0.0;
        // keeps track of max edge weight
        double max = 0.0;
        Set<Integer> visited = new HashSet<>();
        // simple list heap (uses Point as (vertex, distance) pairs)
        List<Point> lst = new ArrayList<>();
        lst.add(s);
        for (int i = 0; i < n; i++) {
            minE[i] = inf;
            // prev[i] = -1;
        }
        minE[0] = 0;
        while (!lst.isEmpty()) {
            int v = deleteMin(lst);
            visited.add(v);
            weight += minE[v];
            // Can I optimize this?
            for (int w = 0; w < n; w++) {
                if (w != v && !visited.contains(w)) {
                    double length = G.weight(v, w);
                    if (minE[w] > length) {
                        minE[w] = length;
                        // prev[w] = v;
                        *//*weight += length;
                        if (length > max) {
                            max = length;
                        }*//*
                        insert(w, minE[w], lst);
                    }
                }
            }
        }
        return weight;
        // return max;
    }

    private static int deleteMin(List<Point> x) {
        if (x.isEmpty()) {
            throw new IllegalArgumentException("heap must not be empty.");
        }
        int ind = -1;
        int v = -1;
        double min = 100.0;
        int size = x.size();
        for (int i = 0; i < size; i++) {
            Point cur = x.get(i);
            double wt = cur.getY();
            if (wt < min) {
                min = wt;
                ind = i;
                v = (int) cur.getX();
            }
        }
        x.remove(ind);
        return v;
    }

    private static void insert(int v, double wt, List<Point> x) {
        double dv = v;
        int n = x.size();
        int i = 0;
        while (i < n) {
            if (x.get(i).getX() == dv) {
                x.remove(i);
                n--;
            }
        }
        Point nu = new Point(dv, wt);
        x.add(nu);
    }*/


    public static double kruskalWeight(CUGraph G) {
        double weight = 0.0;
        double max = 0.0;
        UF kruskUF = new UF(G);
        for (CUGraph.Edge edge_e : G.elst) {
            double cur_wt = edge_e.get_wt();
            int i = edge_e.get_i();
            int j = edge_e.get_j();
            if (kruskUF.find(i) != kruskUF.find(j)) {
                if (cur_wt > max) {
                    max = cur_wt;
                }
                weight += cur_wt;
                kruskUF.union(i, j);
            }
        }
        return weight;
        // return max;
    }


    public static void main(String[] args) {
        int n = 200;
        int dim = 1;
        CUGraph one = new CUGraph(n, dim);
        // System.out.print(one);
        CUGraph two = new CUGraph(n, dim);
        CUGraph three = new CUGraph(n, dim);
        CUGraph four = new CUGraph(n, dim);
        CUGraph five = new CUGraph(n, dim);
        /*CUGraph six = new CUGraph(n, dim);
        CUGraph sev = new CUGraph(n, dim);
        CUGraph eight = new CUGraph(n, dim);
        CUGraph nine = new CUGraph(n, dim);
        CUGraph ten = new CUGraph(n, dim);*/

        /*double max = 0.0;
        for (int i = 0; i < 100; i++) {
            CUGraph now = new CUGraph(n, dim);
            double nowMax = kruskalWeight(now);
            if (nowMax > max) {
                max = nowMax;
            }
        }*/

        double k1 = kruskalWeight(one);
        double k2 = kruskalWeight(two);
        double k3 = kruskalWeight(three);
        double k4 = kruskalWeight(four);
        double k5 = kruskalWeight(five);
        /*double k6 = kruskalWeight(six);
        double k7 = kruskalWeight(sev);
        double k8 = kruskalWeight(eight);
        double k9 = kruskalWeight(nine);
        double k10 = kruskalWeight(ten);*/
        double avg = (k1 + k2 + k3 + k4 + k5) / 5.0;
        System.out.println("MST avg: ");
        /*System.out.println(kruskalWeight(one));
        System.out.println(kruskalWeight(two));
        System.out.println(kruskalWeight(three));
        System.out.println(kruskalWeight(four));
        System.out.println(kruskalWeight(five));
        System.out.println(kruskalWeight(six));
        System.out.println(kruskalWeight(sev));
        System.out.println(kruskalWeight(eight));
        System.out.println(kruskalWeight(nine));
        System.out.println(kruskalWeight(ten));*/
        System.out.println(avg);
        // System.out.println("100 Max: " + max);

    }

}
