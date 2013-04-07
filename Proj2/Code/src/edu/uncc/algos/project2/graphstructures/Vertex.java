package edu.uncc.algos.project2.graphstructures;

import edu.uncc.algos.project2.constants.VertexColor;

public class Vertex {
	
	private int id;
	private int color;
	private int key;
	private int parent;
	
	Vertex(int id){
		this.id = id;
		this.color = VertexColor.WHITE;
		this.parent = 0;
		this.key = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}
}
