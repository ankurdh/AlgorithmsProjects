package edu.uncc.algos.project2.main;

import edu.uncc.algos.project2.graphstructures.Graph;
import edu.uncc.algos.project2.helpers.mst.PrimsMinimalSpanningTree;
import edu.uncc.algos.project2.helpers.shortestpath.DijkstraShortestPath;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		//Read the graph and print the adjacency matrix.
		Graph g = Graph.initGraphFromFile("input4.txt");
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.println("Adjacency Matrix for the input graph: ");
		System.out.println("----------------------------------------------------------------------------------------------------------");
		g.printAdjMatrix(Graph.ADJ_MAT);
		
		//check if the graph is connected.
		System.out.println("\n----------------------------------------------------------------------------------------------------------");
		System.out.println("Evaluating Graph Connectivity using BFS.");
		System.out.println("----------------------------------------------------------------------------------------------------------");
		g.checkConnectivity();
		
		//reset vertexColors for use in MST
		g.resetVertexColors();
		
		//Establish the MST using Prim's Algorithm.
		System.out.println("\n----------------------------------------------------------------------------------------------------------");
		System.out.println("Evaluating Minimal Spanning Tree with Prim's Algorithm.");
		System.out.println("----------------------------------------------------------------------------------------------------------");
		PrimsMinimalSpanningTree primSpanningTree = new PrimsMinimalSpanningTree(g);
		primSpanningTree.findMinimalSpanningTree();
		System.out.println("\nAdjacency Matrix for the Minimal Spanning Forest:");
		System.out.println("----------------------------------------------------------");
		g.printAdjMatrix(Graph.MIN_SPAN_ADJ_MAT);

		//reset the vertex colors for use in the Dijkstra Shortest Path Algorithm
		g.resetVertexColors();
		
		//Find the shortest path from '1' as the source to all the other vertices.
		System.out.println("\n----------------------------------------------------------------------------------------------------------");
		System.out.println("Evaluating Shortest Paths from Vertex 1 to the rest using Dijkstra's Algorithm.");
		System.out.println("----------------------------------------------------------------------------------------------------------");
		if(g.isConnected()){
			DijkstraShortestPath shortestPath = new DijkstraShortestPath(g);
			shortestPath.findShortestPaths();
			System.out.println("\nAdjacency matrix for Shortest paths.");
			System.out.println("------------------------------------------------------");
			g.printAdjMatrix(Graph.SHORTEST_PATH_MAT);
		} else {
			System.out.println("Graph is not connected. Not running Dijkstra's algorithm on it.");
		}
	}
}