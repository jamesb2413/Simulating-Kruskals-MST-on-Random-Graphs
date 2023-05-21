# Simulating-Kruskals-MST-on-Random-Graphs
A project for Harvard's Data Structures and Algorithms course. Simulating Kruskal's Minimum Spanning Tree algorithm on random, complete, undirected graphs. A graph with n vertices is complete if all n
choose 2 pairs of vertices are edges in the graph.

I run simulations on three types of graphs:
1. Complete graphs on n vertices, where the weight of each edge is a real number chosen uniformly at
random on [0, 1].
2. Complete graphs on n vertices, where the vertices are points chosen uniformly at random inside the
unit square. The weight of an edge is the Euclidean distance between its endpoints.
3. Complete graphs on n vertices, where the vertices are points chosen uniformly at random inside the
unit cube (3 dimensions) and hypercube (4 dimensions). Again, the weight of an edge is the Euclidean distance between its endpoints.

The following graphs simulate how the expected (average) weight of the minimum
spanning tree returned by Kruskal's algorithm grows as a function of n. To do this, I implemented Kruskal's MST algorithm, as well as procedures to generate the random graphs.

For brevity, I refer to the first case, where the weights are chosen at random on [0, 1], as 1- Dimension, the second case, where the points are chosen at random inside the unit square, as 2-Dimension, the unit cube case as 3-Dimension, and the hypercube case as 4-Dimension.

The following table displays the expected MST weights as raw data depenedent on n and type of graph (number of dimensions):
<img width="737" alt="Screen Shot 2023-05-21 at 4 59 05 PM" src="https://github.com/jamesb2413/Simulating-Kruskals-MST-on-Random-Graphs/assets/43123401/c058cfb7-b328-48bf-9077-4dd3960074ec">
