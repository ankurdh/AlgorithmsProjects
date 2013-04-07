package edu.uncc.algos.project2.graphstructures;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.uncc.algos.project2.constants.VertexColor;

public class Graph {
	
	private final Integer VERTICES;
	private final Integer EDGES;
	
	private List<Vertex> vertices;
	private List<Edge> edges;
	
	private boolean isConnected;
	
	private List<Integer> componentStartVertex;
	
	private float adjMatrix[][];
	private float adjMatrixForMinimalSpanningTree[][];
	private float adjMatrixForShortestPaths[][];
	
	public static final int ADJ_MAT = 1;
	public static final int MIN_SPAN_ADJ_MAT = 2;
	public static final int SHORTEST_PATH_MAT = 3;
	
	public Graph(Integer v, Integer e){
		VERTICES = v;
		EDGES = e;
		
		adjMatrix = new float[VERTICES+1][VERTICES+1];
		adjMatrixForMinimalSpanningTree = new float[VERTICES+1][VERTICES+1];
		adjMatrixForShortestPaths = new float[VERTICES+1][VERTICES+1];
		
		vertices = new ArrayList<Vertex>(VERTICES+1);
		edges = new ArrayList<Edge>(EDGES+1);
		
		for(int i = 1 ; i < VERTICES + 1 ; i ++)
			vertices.add(new Vertex(i));
		
		componentStartVertex = new ArrayList<Integer>();
		setConnected(true);
	}
	
	public void setMinimalSpanningTreeAdjMatrixValue(Integer sourceVertex, Integer destinationVertex, float cost){
		adjMatrixForMinimalSpanningTree[sourceVertex][destinationVertex] = 
				adjMatrixForMinimalSpanningTree[destinationVertex][sourceVertex] = cost;
	}
	
	public void setShortestPathAdjMatrixValue(Integer destVertex, Integer throughVertex, float cost){
		adjMatrixForShortestPaths[destVertex][throughVertex] = 
				adjMatrixForShortestPaths[throughVertex][destVertex] = cost;
		
	}
	
	public List<Integer> getComponentStartVertices(){
		return componentStartVertex;
	}
	
	public void addEdge(Integer startVertex, Integer endVertex, float weight){
		adjMatrix[startVertex][endVertex] = adjMatrix[endVertex][startVertex] = weight;
		
		edges.add(new Edge(startVertex, endVertex, weight));
	}
	
	public void printAdjMatrix(int matrixType){
		
		if(matrixType == Graph.MIN_SPAN_ADJ_MAT){
			for(int i = 1; i < VERTICES + 1 ; i ++){
				for(int j = 1; j < VERTICES + 1; j ++)
					System.out.print(adjMatrixForMinimalSpanningTree[i][j]+"\t" );
				System.out.println();
			}
		} else if(matrixType == Graph.ADJ_MAT){
			for(int i = 1; i < VERTICES + 1 ; i ++){
				for(int j = 1; j < VERTICES + 1; j ++)
					System.out.print(adjMatrix[i][j]+"\t");
				System.out.println();
			}
		} else {
			for(int i = 1; i < VERTICES + 1 ; i ++){
				for(int j = 1; j < VERTICES + 1; j ++)
					System.out.print(adjMatrixForShortestPaths[i][j]+"\t" );
				System.out.println();
			}
		}
	}
	
	public static Graph initGraphFromFile(String inputFile){
		
		Graph g;
		
		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedReader br = null;
		
		try{
			  fstream = new FileInputStream(inputFile);
			  in = new DataInputStream(fstream);
			  br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  
			  g = new Graph(Integer.parseInt(br.readLine().trim()), Integer.parseInt(br.readLine().trim()));
			  
			  while ((strLine = br.readLine()) != null)   {
				  String [] str = strLine.split(",");
				  g.addEdge(Integer.parseInt(str[0].trim()), Integer.parseInt(str[1].trim()), Float.parseFloat(str[2].trim()));
			  }
			  
			  return g;
			  
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void setVertexColor(Integer vertexId, int color){
		for(Vertex v : vertices)
			if(v.getId() == vertexId)
				v.setColor(color);
	}
	
	private int getVertexColor(Integer vertexId){
		for(Vertex v : vertices)
			if(v.getId() == vertexId)
				return v.getColor();
		
		return -1;
	}
	
	private void doBFS(Vertex v){
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v.getId());
		
		while(!queue.isEmpty()){
			Integer vertexId = queue.remove();
			for(Integer i : getAdjecentVertices(vertexId)){
				if(getVertexColor(i) == VertexColor.WHITE)
					queue.add(i);
			setVertexColor(vertexId, VertexColor.BLACK);
			}
		}
	}
	
	public List<Integer> getAdjecentVertices(int vertexId){
		List<Integer> adjVertices = new ArrayList<Integer>();
		
		for(int i = 1 ; i < VERTICES + 1 ; i ++)
			if(adjMatrix[vertexId][i] != 0.0)
				adjVertices.add(i);
				
		return adjVertices;
	}

	public void checkConnectivity() {
		
		for(Vertex v : vertices){
			if(v.getColor() == VertexColor.WHITE){
				componentStartVertex.add(v.getId());
				doBFS(v);
			}
		}
		
		if(componentStartVertex.size() > 1){
			System.out.println("Graph is not connected. Has " + componentStartVertex.size() + " components.");
			isConnected = false;
		} else {
			System.out.println("Graph is connected.");
			isConnected = true;
		}
		
	}
	
	public void resetVertexColors(){
		for(Vertex v : vertices)
			v.setColor(VertexColor.WHITE);
	}

	public boolean isConnected() {
		return isConnected;
	}
	
	public void setConnected(boolean isConnected){
		this.isConnected = isConnected;
	}
	
	public int getNoOfVertices(){
		return VERTICES;
	}

	public float getEdgeWeight(int sourceVertex, int destVertex) {
		return adjMatrix[sourceVertex][destVertex];
	}

	public void setVertexProcessed(int vertex) {
		vertices.get(vertex - 1).setColor(VertexColor.BLACK);
	}

	public boolean isVertexProcessed(Integer adjacentVertex) {
		if(vertices.get(adjacentVertex - 1).getColor() == VertexColor.BLACK)
			return true;
		
		return false;
	}
}