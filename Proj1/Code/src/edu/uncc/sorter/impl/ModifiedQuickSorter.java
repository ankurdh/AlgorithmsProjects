package edu.uncc.sorter.impl;

import edu.uncc.sorter.AbstractQuickSorters;

public class ModifiedQuickSorter extends AbstractQuickSorters {
	
	private Integer insertionSortThreshold;
	
	public ModifiedQuickSorter(){
		super();
		insertionSortThreshold = 5; //set 5 by default
	}
	
	public ModifiedQuickSorter(int i) {
		insertionSortThreshold = i;
	}

	private void modifiedInsertionSort(int [] arr, int start, int end){

		int length = (end - start + 1);

		for(int j = start + 1; j < length + start ; j ++)
		{
			int key = arr[j];
			int i = j - 1;

			while(true){
				comparisonCounter++;
				if(arr[i] > key){
					if(i >= start){
						assignmentCounter++;
						int temp = arr[i+1];
						arr[i+1] = arr[i];
						arr[i] = temp;
						i--;
					}
					if(i < 0)
						break;
				} else 
					break;
			}
			arr[i+1] = key;
		}
	}
	
	public void setInsertionSortThreshold(Integer threshold){
		insertionSortThreshold = threshold;
	}
		
	@Override
	public void sort(int [] arr, int start, int end){
		int lengthOfArray = end - start + 1;

		if(start < end){
			if(lengthOfArray <= insertionSortThreshold)
				modifiedInsertionSort(arr, start, end);
			
			else {
					int pivot = partition(arr, start, end);
			
					sort(arr, start, pivot - 1);
					sort(arr, pivot + 1, end);
			}
		}
	}

	@Override
	public void sortAndPrint(int[] arr, int start, int end, String msg) {
		super.sortAndPrintArray(arr, start, end, msg);
		System.out.println("Insertion sort threshold: " + insertionSortThreshold);
	}
}
