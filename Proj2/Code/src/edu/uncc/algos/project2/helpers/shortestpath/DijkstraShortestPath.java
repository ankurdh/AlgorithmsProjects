package edu.uncc.algos.project2.helpers.shortestpath;

import edu.uncc.algos.project2.graphstructures.Graph;
import edu.uncc.algos.project2.helpers.PriorityQueue;
import edu.uncc.algos.project2.helpers.PriorityQueueElement;

public class DijkstraShortestPath {
	
	private PriorityQueue pq;
	private Graph g;
	
	public DijkstraShortestPath(Graph g){
		this.g = g;
		pq = new PriorityQueue(g.getNoOfVertices());
	}
	
	public void findShortestPaths(){
		
		pq.insert(1, -1, 0.0f);
		
		while(!pq.isEmpty()){
			PriorityQueueElement vertex = pq.delete();
			
			for(Integer adjacentVertex : g.getAdjecentVertices(vertex.getVertex())){
				if(!pq.checkAndUpdate(adjacentVertex, vertex.getVertex(), vertex.getCost() + g.getEdgeWeight(vertex.getVertex(), adjacentVertex))) {
					if(!g.isVertexProcessed(adjacentVertex))
						pq.insert(adjacentVertex, vertex.getVertex(), vertex.getCost() + g.getEdgeWeight(vertex.getVertex(), adjacentVertex));
				}
			}
			
			if(vertex.getParent() != -1){
				g.setShortestPathAdjMatrixValue(vertex.getVertex(), vertex.getParent(), vertex.getCost());
				System.out.println("Path from 1 to " + vertex.getVertex()  + " is via " + vertex.getParent()+ " with cost: " + vertex.getCost());
			}
			g.setVertexProcessed(vertex.getVertex());
			
		}
	}
}