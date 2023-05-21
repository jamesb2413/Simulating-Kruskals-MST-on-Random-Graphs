public class MST {

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

        double k1 = kruskalWeight(one);
        double k2 = kruskalWeight(two);
        double k3 = kruskalWeight(three);
        double k4 = kruskalWeight(four);
        double k5 = kruskalWeight(five);

        double avg = (k1 + k2 + k3 + k4 + k5) / 5.0;
        System.out.println("MST avg: ");

        System.out.println(avg);

    }

}
