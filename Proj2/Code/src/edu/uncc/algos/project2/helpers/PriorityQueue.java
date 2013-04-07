package edu.uncc.algos.project2.helpers;

import java.util.Comparator;

public class PriorityQueue {
	
	private java.util.PriorityQueue<PriorityQueueElement> pq;
	
	public PriorityQueue(int noOfVertices){		
		pq = new java.util.PriorityQueue<PriorityQueueElement>(noOfVertices, new PrioQueueElementComparator());		
	}
	
	public void insert(int vertex, int parent, float cost){
		pq.add(new PriorityQueueElement(vertex, parent, cost));		
	}
	
	public PriorityQueueElement delete(){
		return pq.poll();
	}
	
	public void update(int vertex, int parent, float cost){
		PriorityQueueElement elementToUpdate = null;
		for(PriorityQueueElement pqe : pq){
			if(pqe.getVertex() == vertex){
				elementToUpdate = pqe;
				break;
			}
		}
		
		pq.remove(elementToUpdate);
		pq.add(new PriorityQueueElement(vertex, parent, cost));		
	}
	
	public boolean checkAndUpdate(int vertex, int parent, float cost){
		boolean returnValue = false;
		PriorityQueueElement elementToUpdate = null;
		
		for(PriorityQueueElement pqe : pq){
			if(pqe.getVertex() == vertex){
				returnValue = true;
				if(pqe.getCost() > cost){
				elementToUpdate = pqe;
				break;
				}
			}
		}
		
		if(elementToUpdate != null){
			pq.remove(elementToUpdate);
			pq.add(new PriorityQueueElement(vertex, parent, cost));
		}		
		
		return returnValue;
	}
	
	public boolean isEmpty(){
		return pq.isEmpty();
	}
	
	public float contains(int vertex){
		for(PriorityQueueElement pqe : pq)
			if(pqe.getVertex() == vertex)
				return pqe.getCost();
			return -1.0f;
	}
	
	public PriorityQueueElement getElementWithVertex(int vertex){
		for(PriorityQueueElement pqe : pq)
			if(pqe.getVertex() == vertex)
				return pqe;
		return null;
	}
}

class PrioQueueElementComparator implements Comparator<PriorityQueueElement>{
	@Override
	public int compare(PriorityQueueElement arg0, PriorityQueueElement arg1) {
		if(arg0.getCost() < arg1.getCost())
			return -1;
		else if(arg0.getCost() > arg1.getCost())
			return 1;
		return 0;
	}
	
}
