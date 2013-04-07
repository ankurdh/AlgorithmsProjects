package edu.uncc.sorter;

import edu.uncc.helpers.ArrayHelper;

public abstract class AbstractQuickSorters {

	protected long comparisonCounter;
	protected long assignmentCounter;
	
	public AbstractQuickSorters(){
		resetComparisonCounter();
		resetAssignmentCounter();
	}
	
	public long getAssignmentCounter() {
		return assignmentCounter;
	}

	public void setAssignmentCounter(int assignmentCounter) {
		this.assignmentCounter = assignmentCounter;
	}

	protected int partition(int [] arr, int start, int end){
		int pivot = arr[end];
		int i = start - 1;
		
		for(int j = start; j < end ; j ++)
		{
			comparisonCounter++;
			if(arr[j] <= pivot)
			{
				i++;
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				assignmentCounter++;
			}
		}

		arr[end] = arr[i + 1];
		arr[i + 1] = pivot;
		assignmentCounter++;

		return i + 1;
	}
	
	public void resetComparisonCounter(){
		setComparisonCounter(0);
	}
	
	public void resetAssignmentCounter(){
		setAssignmentCounter(0);
	}

	public long getComparisonCounter() {
		return comparisonCounter;
	}

	public void setComparisonCounter(int comparisonCounter) {
		this.comparisonCounter = comparisonCounter;
	}	

	public void sortAndPrintArray(int [] arr, int start, int end, String msgToPrint){
		sort(arr, start, end);
		ArrayHelper.printArray(arr, msgToPrint);
		System.out.println("Comparisons: " + getComparisonCounter());
	}
	
	public abstract void sortAndPrint(int [] arr, int start, int end, String msg);
	
	public abstract void sort(int [] arr, int start, int end);
}
