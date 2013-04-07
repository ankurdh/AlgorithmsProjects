package edu.uncc.main;

import edu.uncc.helpers.StatsHelper;
import edu.uncc.helpers.TestHelper;

public class Main {
	
	public static void main(String[] args) {
		
		TestHelper testHelper = new TestHelper();
		
		if(args.length > 0){
			for(String s : args){
				if(s.equals("test_correctness"))
					testHelper.testCorrectnessOfAlgorithms(20, 8, 4);
				else if (s.equals("plot_graphs"))
					StatsHelper.setPlotGraphs(true);
			}				
		}
	
		//Do the tests.
		testHelper.testAlgorithms();		
		
	}
}
