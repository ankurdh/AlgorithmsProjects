package edu.uncc.algos.project2.helpers.mst;

import edu.uncc.algos.project2.graphstructures.Graph;
import edu.uncc.algos.project2.helpers.PriorityQueue;
import edu.uncc.algos.project2.helpers.PriorityQueueElement;

public class PrimsMinimalSpanningTree {
	
	private Graph g;
	private PriorityQueue pq;
	
	public PrimsMinimalSpanningTree(Graph g){
		this.g = g;
		pq = new PriorityQueue(g.getNoOfVertices());
	}
	
	public void findMinimalSpanningTree(){
		
		for(Integer i : g.getComponentStartVertices()){
			System.out.println("\nEvaluating sub component with vertex '" + i + "'");
			System.out.println("----------------------------------------------------------");
			pq.insert(i, -1, 0.0f);
			
			while(!pq.isEmpty()){
				PriorityQueueElement vertex = pq.delete();
				
				for(Integer adjacentVertex : g.getAdjecentVertices(vertex.getVertex())){
					if(!pq.checkAndUpdate(adjacentVertex, vertex.getVertex(), g.getEdgeWeight(vertex.getVertex(), adjacentVertex))) {
						if(!g.isVertexProcessed(adjacentVertex))
							pq.insert(adjacentVertex, vertex.getVertex(), g.getEdgeWeight(vertex.getVertex(), adjacentVertex));
					}
				}
				
				if(vertex.getParent() != -1){
					g.setMinimalSpanningTreeAdjMatrixValue(vertex.getParent(), vertex.getVertex(), vertex.getCost());
					System.out.println("Edge from " + vertex.getParent() + " to " + vertex.getVertex() + " is in the min span tree with cost: " + vertex.getCost());
				}
				g.setVertexProcessed(vertex.getVertex());
			}
		}		
	}
}
