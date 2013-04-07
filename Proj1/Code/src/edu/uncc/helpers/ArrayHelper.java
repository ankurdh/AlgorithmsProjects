package edu.uncc.helpers;

public class ArrayHelper {
	
	public static void printArray(int [] arr, String msg){
		System.out.print(msg);
		
		for(int i = 0 ; i < arr.length ; i ++)
			System.out.print(arr[i] + " ");
		
		System.out.println();
	}
}
