package edu.uncc.helpers;

import java.util.Random;

import edu.uncc.constants.Constants;

public class RandomNumberHelper {
	
	private static int [] seeds;
	private static int seedCounter;
	private static Random randGenerator;
	
	static {
		randGenerator = new Random();
		randGenerator.setSeed(Constants.INITIAL_SEED);
		seeds = new int [Constants.MAX_ITER_FOR_AVERAGE];
		
		for(int i = 0 ; i < Constants.MAX_ITER_FOR_AVERAGE ; i ++)
			seeds[i] = randGenerator.nextInt(Constants.MAX_RAND_NO_FOR_SEEDS);
		
		seedCounter = 0;
	}
	
	public static void fillArrayWithRandomNumbers(int [] arr, int maxRandNo, int seedIndex){
		fillArrayWithRandomNumbers(arr,maxRandNo, 0 , seedIndex);
	}
	
	public static void fillArrayWithRandomNumbers(int [] arr, int maxRandNo){
		fillArrayWithRandomNumbers(arr, maxRandNo, 0, -1);
	}
		
	public static void fillArrayWithRandomNumbers(int [] arr, int maxRandNo, int noOfElements, int seedIndex){
		
		if(seedIndex == -1)
			randGenerator.setSeed(seeds[seedCounter++]);
		else 
			randGenerator.setSeed(seeds[seedIndex]);
		
		if(noOfElements != 0){
			for(int i = 0 ; i < noOfElements ; i ++)
				arr[i] = randGenerator.nextInt(maxRandNo);
		} else {
			for(int i = 0 ; i < Constants.MAX_ARRAY_SIZE ; i ++)
				arr[i] = randGenerator.nextInt(maxRandNo);
		}		
	}
	
	public static void resetSeedsCounter(){
		seedCounter = 0;
	}
}
