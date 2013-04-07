package edu.uncc.algos.project2.helpers;

public class PriorityQueueElement {
		private int vertex;
		private int parent;
		private float cost;
		
		public PriorityQueueElement(int v, int p, float c){
			vertex = v;
			parent = p;
			cost = c;
		}
		
		public void setParent(int p){
			parent = p;
		}
		
		public void setCost(float c){
			cost = c;
		}
		
		public int getVertex(){
			return vertex;
		}
		
		public int getParent(){
			return parent;
		}
		
		public float getCost(){
			return cost;
		}
	}
