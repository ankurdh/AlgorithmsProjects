package edu.uncc.sorter.impl;

import edu.uncc.sorter.AbstractQuickSorters;

public class QuickSort extends AbstractQuickSorters {
	
	public QuickSort(){
		super();
	}
		
	@Override
	public void sort(int [] arr, int start, int end){
		if(start < end)
		{
			int pivot = partition(arr, start, end);
			sort(arr, start, pivot - 1);
			sort(arr, pivot + 1, end);
		}
	}

	@Override
	public void sortAndPrint(int[] arr, int start, int end, String msg) {
		super.sortAndPrintArray(arr, start, end, msg);
	}
	
}
