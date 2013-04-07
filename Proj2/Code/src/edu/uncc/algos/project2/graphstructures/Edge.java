package edu.uncc.algos.project2.graphstructures;

public class Edge {
	
	private int startVertex;
	private int endVertex;
	private float weight;
	
	public Edge(int s, int e, float w){
		startVertex = s;
		endVertex = e;
		weight = w;
	}
	
	public float getWeight(){
		return weight;
	}
	
	public int getStartVertex(){
		return startVertex;
	}
	
	public int getEndVertex(){
		return endVertex;
	}
}
