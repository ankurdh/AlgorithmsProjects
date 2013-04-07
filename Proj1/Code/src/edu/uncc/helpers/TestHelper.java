package edu.uncc.helpers;

import edu.uncc.constants.Constants;
import edu.uncc.sorter.impl.ModifiedQuickSorter;
import edu.uncc.sorter.impl.QuickSort;

public class TestHelper {
	
	public void testCorrectnessOfAlgorithms(int arraySize, int maxRandNo, int insertionSortThreshold){

		//declare arrays for the normal and the modified quick sorters.
		int [] arrayForModifiedQuickSorter = new int[arraySize] ;
		int [] arrayForNormalQuickSorter = new int[arraySize] ;
		
		//Initialize the arrays with the same set of random numbers.
		RandomNumberHelper.resetSeedsCounter();
		RandomNumberHelper.fillArrayWithRandomNumbers(arrayForNormalQuickSorter, maxRandNo, arraySize, 0);
		
		RandomNumberHelper.resetSeedsCounter();
		RandomNumberHelper.fillArrayWithRandomNumbers(arrayForModifiedQuickSorter, maxRandNo, arraySize, 0);
		
		ModifiedQuickSorter modifiedQuickSorter = new ModifiedQuickSorter(insertionSortThreshold);
		QuickSort quickSorter = new QuickSort();
		
		ArrayHelper.printArray(arrayForNormalQuickSorter, "Array for normal   quick sorter: ");
		ArrayHelper.printArray(arrayForModifiedQuickSorter, "Array for modified quick sorter: ");
		
		modifiedQuickSorter.sortAndPrint(arrayForModifiedQuickSorter, 0, arraySize - 1, "Sorted by modified quick sort: ");
		quickSorter.sortAndPrint(arrayForNormalQuickSorter, 0, arraySize - 1, "Sorted by normal quick sort: ");
	}
	
	public void testAlgorithms(){
		
		//Declare and initialize the sorter objects.
		ModifiedQuickSorter modifiedQuickSorter = new ModifiedQuickSorter();
		QuickSort quickSorter = new QuickSort();
		
		//Declare arrays for holding the testing parameters.
		Integer [] maxRandNos = {100, 1000, 10000, 100000};
		Integer [] insSortThresholds = { 3, 4, 5, 6, 7, 8, 10, 12, 15, 18, 20, 25, 50, 75, 100, 120, 130, 140, 150};
		
		//Initialize the StatsHelper
		StatsHelper.initializeStatsMaps(insSortThresholds);
		
		//Declare an array to hold values for sorting
		int [] arrayToSort = new int[Constants.MAX_ARRAY_SIZE];
		
		for(Integer maxRandNo : maxRandNos){
						
			//Below loop for calculating the counts of modified quick sorter.
			//Run CONSTANTS.MAX_ITER_FOR_AVERAGE times to get an average.
			for(int i = 0 ; i < Constants.MAX_ITER_FOR_AVERAGE ; i ++){
				
				//Run for each integer threshold.
				for(Integer insSortThreshold : insSortThresholds){
					//Get an array. 
					RandomNumberHelper.fillArrayWithRandomNumbers(arrayToSort, maxRandNo, i);
					
					//set the threshold and sort the array.
					modifiedQuickSorter.setInsertionSortThreshold(insSortThreshold);
					modifiedQuickSorter.sort(arrayToSort, 0, Constants.MAX_ARRAY_SIZE - 1);
					
					//save the comparisons and assignments
					StatsHelper.addComparisonValueAgainstKey(insSortThreshold, modifiedQuickSorter.getComparisonCounter());
					StatsHelper.addAssignmentValueAgainstKey(insSortThreshold, modifiedQuickSorter.getAssignmentCounter());
					
					//reset the comparisons, since we use the same sorter object.
					modifiedQuickSorter.resetComparisonCounter();
					modifiedQuickSorter.resetAssignmentCounter();					
				}
				
				//Below loop for calculating counts of the normal quick sorter.
				//Get the same array.
				RandomNumberHelper.fillArrayWithRandomNumbers(arrayToSort, maxRandNo, i);
				
				//sort and save counts.
				quickSorter.sort(arrayToSort, 0, Constants.MAX_ARRAY_SIZE - 1);
				
				//Save the count
				StatsHelper.addValueInNormalSorterComparisonList(quickSorter.getComparisonCounter());
				StatsHelper.addValueInNormalSorterAssignmentList(quickSorter.getAssignmentCounter());
				
				//reset the sorter counter.
				quickSorter.resetComparisonCounter();
				quickSorter.resetAssignmentCounter();
			}		
			
			StatsHelper.computeAndPrintStats(maxRandNo);
		}
	}	
}
