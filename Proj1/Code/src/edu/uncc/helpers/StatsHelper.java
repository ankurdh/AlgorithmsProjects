package edu.uncc.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.uncc.graphs.GraphPlotter;

public class StatsHelper {
	
	private static HashMap<Integer, List<Long>> comparisonStatsMap;
	private static HashMap<Integer, List<Long>> assignmentStatsMap;
	
	private static List<Long> normalSorterComparisonsList;
	private static List<Long> normalSorterAssignmentList;
	
	private static MinValue minComparisons;
	private static MinValue minAssignments;
	
	private static boolean plotGraphs;
	
	static {
		comparisonStatsMap = new HashMap<Integer, List<Long>>();
		assignmentStatsMap = new HashMap<Integer, List<Long>>();
		
		normalSorterComparisonsList = new ArrayList<Long>();
		normalSorterAssignmentList = new ArrayList<Long>();
		
		minComparisons = new MinValue();
		minAssignments = new MinValue();
		
		//don't plot graphs by default.
		plotGraphs = false; 
	}	

	public static void setPlotGraphs(boolean plotGraphs) {
		StatsHelper.plotGraphs = plotGraphs;
	}

	public static void initializeStatsMaps(Integer [] keys){
		for(Integer key : keys){
			comparisonStatsMap.put(key, new ArrayList<Long>());
			assignmentStatsMap.put(key, new ArrayList<Long>());
		}
	}
	
	public static void addComparisonValueAgainstKey(Integer key, Long value){
		comparisonStatsMap.get(key).add(value);
	}
	
	public static void addAssignmentValueAgainstKey(Integer key, Long value){
		assignmentStatsMap.get(key).add(value);
	}
	
	public static void addValueInNormalSorterComparisonList(Long l){
		normalSorterComparisonsList.add(l);
	}
	
	public static void addValueInNormalSorterAssignmentList(Long l){
		normalSorterAssignmentList.add(l);
	}

	public static void computeAndPrintStats(Integer maxRandNo) {
		
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("Stats for random numbers less than " + maxRandNo);
		System.out.println("----------------------------------------------------------------------------------");
		
		Object[] insSortThresholds = comparisonStatsMap.keySet().toArray();
		Arrays.sort(insSortThresholds);
				
		for(Object key : insSortThresholds){
			long comparisonAverage = 0;
			long assignmentAverage = 0;
			
			for(Long count : comparisonStatsMap.get(key))
				comparisonAverage += count;
			
			for(Long count : assignmentStatsMap.get(key))
				assignmentAverage += count;
			
			comparisonAverage /= comparisonStatsMap.get(key).size();
			assignmentAverage /= assignmentStatsMap.get(key).size();
			
			if(minComparisons.getValue() == 0 || comparisonAverage < minComparisons.getValue()){
				minComparisons.setValue(comparisonAverage);
				minComparisons.setKey((Integer) key);
			}
			
			if(minAssignments.getValue() == 0 || assignmentAverage < minAssignments.getValue()){
				minAssignments.setValue(assignmentAverage);
				minAssignments.setKey((Integer) key);
			} 
			
			System.out.printf("Avg. (comparisons, assignments) with ins. sort threshold %3d: (%7d, %7d )\n", key, comparisonAverage, assignmentAverage);			
		}
		
		long averageComparisons = 0;
		long averageAssignments = 0;
		
		for(Long i : normalSorterComparisonsList)
			averageComparisons += i;
		
		for(Long i : normalSorterAssignmentList)
			averageAssignments += i;
		
		System.out.println("\nAverage comparisons with normal quick sort: " + (averageComparisons/normalSorterComparisonsList.size()));
		System.out.println("Average assignments with normal quick sort: " + (averageAssignments/normalSorterComparisonsList.size()) + "\n");
		
		System.out.println("Minimum most comparisons of " + minComparisons.getValue() + " were done with insertion sort threshold " + minComparisons.getKey());
		System.out.println("Minimum most assignments of " + minAssignments.getValue() + " were done with insertion sort threshold " + minAssignments.getKey());
		
		if(plotGraphs)
			GraphPlotter.plotGraph(comparisonStatsMap, assignmentStatsMap, normalSorterComparisonsList, normalSorterAssignmentList, "Graph for numbers less than " + maxRandNo);
		
		//clear the data structures.
		resetDataStructures();
		
	}

	private static void resetDataStructures() {
		
		for(Integer i : comparisonStatsMap.keySet()){
			comparisonStatsMap.get(i).clear();
			assignmentStatsMap.get(i).clear();
		}
			
		normalSorterComparisonsList.clear();
		normalSorterAssignmentList.clear();
		
		minComparisons.reset();
		minAssignments.reset();
		
	}
}

class MinValue {
	Integer key;
	long value;
	
	public MinValue(){
		key = 0;
		value = 0;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer insSortThreshold) {
		this.key = insSortThreshold;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long avgComparisons) {
		this.value = avgComparisons;
	}	
	
	public void reset(){
		setKey(0);
		setValue(0);
	}
}
