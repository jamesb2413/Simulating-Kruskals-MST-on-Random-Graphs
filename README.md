# Simulating Kruskal's MST Algorithm on Random Graphs in Multiple Dimensions
A project for Harvard's Data Structures and Algorithms course. Simulating Kruskal's Minimum Spanning Tree algorithm on random, complete, undirected graphs. A graph with n vertices is complete if all n choose 2 pairs of vertices are edges in the graph.

I run simulations on three types of graphs:
1. Complete graphs on n vertices, where the weight of each edge is a real number chosen uniformly at
random on [0, 1].
2. Complete graphs on n vertices, where the vertices are points chosen uniformly at random inside the
unit square. The weight of an edge is the Euclidean distance between its endpoints.
3. Complete graphs on n vertices, where the vertices are points chosen uniformly at random inside the
unit cube (3 dimensions) and hypercube (4 dimensions). Again, the weight of an edge is the Euclidean distance between its endpoints.

# Code Layout
* Point.java implements nodes within random graphs as points in 1, 2, 3, and 4 dimensions
* CUGraph.java implements random graphs using lists of Points 
* UF.java implements the Union Find algorithm for use within Kruskal's algorithm
* MST.java finds a minimum spanning tree using Kruskal's algorithm, which sorts all edges in increasing order by weight, then examining all edges in order, discarding any edges whose endpoints are both in the same tree. For all edges with endpoints in different trees, the two trees containing its endpoints are merged into the tree that will be eventually returned. Since our algorithm maintains a collection of disjoint sets of vertices in each tree, we use Union Find to determine the tree of each vertex and join trees when edges are added to our MST. Kruskal's is made more efficient here by pruning edges with sufficiently high weights such that they are very unlikely to be included in an MST, as estimated by simulating the algorithm on small trees.

To run your own simulation, after cloning this repo, compile using
`javac MST.java`
and run with 
`java MST`
The dimension and size of the simulated graph, as well as the number of simulations contributing to the average weight computation, can be modified in the `main()` method in MST.java.

# Report
The following plots simulate how the expected (average) weight of the minimum
spanning tree returned by Kruskal's algorithm grows as a function of n. To do this, I implemented Kruskal's MST algorithm, as well as procedures to generate the random graphs.

For brevity, I refer to the first case, where the weights are chosen at random on [0, 1], as 1- Dimension, the second case, where the points are chosen at random inside the unit square, as 2-Dimension, the unit cube case as 3-Dimension, and the hypercube case as 4-Dimension.

## Data:
The following table displays the expected MST weights as raw data depenedent on n and type of graph (number of dimensions):

<img width="737" alt="Screen Shot 2023-05-21 at 4 59 05 PM" src="https://github.com/jamesb2413/Simulating-Kruskals-MST-on-Random-Graphs/assets/43123401/c058cfb7-b328-48bf-9077-4dd3960074ec">

## Plots:
1-Dimension average tree size growth function plots:

<img width="1058" alt="Screen Shot 2023-05-21 at 5 01 15 PM" src="https://github.com/jamesb2413/Simulating-Kruskals-MST-on-Random-Graphs/assets/43123401/73a0bb33-425f-4497-9a72-c99781c93e63">

2-Dimension average tree size growth function plots:

<img width="1055" alt="Screen Shot 2023-05-21 at 5 01 39 PM" src="https://github.com/jamesb2413/Simulating-Kruskals-MST-on-Random-Graphs/assets/43123401/dca8bf3c-90a9-48aa-9984-cac0119427a4">

3-Dimension average tree size growth function plots:

<img width="1047" alt="Screen Shot 2023-05-21 at 5 02 00 PM" src="https://github.com/jamesb2413/Simulating-Kruskals-MST-on-Random-Graphs/assets/43123401/fe072dca-500f-4f2e-8a91-81ec7dd7206f">

4-Dimension average tree size growth function plots:

<img width="1049" alt="Screen Shot 2023-05-21 at 5 02 18 PM" src="https://github.com/jamesb2413/Simulating-Kruskals-MST-on-Random-Graphs/assets/43123401/b2a2ba56-913e-4646-a4f3-77d7a1b853d0">

## Growth Functions:
For the 1-Dimension case, f(n) = 1.2. The tree size did not change as a function of n. Average tree sizes were about 1.2 for all values of n.

For the 2-Dimension case, f(n) = 0.6831n0.4952. Average tree sizes increased as a function of n, but the rate of increase of the tree sizes with respect to n decreases as n increases. In other words, the second derivative is negative, consistent with this f(n) and our plots.

For the 3-Dimension case, f(n) = 0.0.7126n0.6585. For the 3-Dimension case, average tree sizes increased as a function of n at a greater rate than in the 2-Dimension case. Again, tree size growth slowed (negative second derivative).

For the 4-Dimension case, f(n) = 0.7837n0.7384. Tree size increased as a function of n at a still greater rate than in the 2-D and 3-D cases. Again, tree size growth slowed (negative second derivative).

## Discussion: 
The growth rates are not surprising if we reason about the computations. In the 1-D case, all edges have weight between zero and one with a normal distribution. As n increases, the average weights of individual edges in the MST go down because there are more edges in the distribution to choose from, but the number of edges in the MST goes up because there are more vertices to connect. These trends exactly cancel each other out, so the total weight of the MST stays constant on average.

In the 2-D case, weights can be greater than the 1-D case, so the distribution of weights is skewed to be larger. As a result of this skew in weights, as n increases, the average weights of individual edges in the MST go down slower as the number of edges in the MST goes up, so the balance from 1-D is lost, and the total weight of the MST increases as a function of n. The slope of the increase decreases with n (negative 2nd derivative) because as n increases, the vertices get more concentrated in the unit square, so MSTs can be formed with smaller edges.

The same reasoning follows for the 3-D and 4-D cases, but the slopes decrease more slowly because the spaces (unit cube and hypercube) are respectively larger, so concentration of vertices has less of an effect.

I trusted the pseudorandom number generator from the Java Math library and I did not notice any issues with it.
