package edu.uncc.graphs;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

@SuppressWarnings("serial")
public class GraphPlotter extends JFrame {
		
    public GraphPlotter(HashMap<Integer,List<Long>> comparisonsStats, HashMap<Integer, List<Long>> assignmentStats, 
    		List<Long> normalSorterComparisonList, List<Long> normalSorterAssignmentList, final String title) {
        super(title);
        
        final XYDataset dataset = createDataChartWith(comparisonsStats, assignmentStats, normalSorterComparisonList, normalSorterAssignmentList);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
        
    }
    
    public XYDataset createDataChartWith(HashMap<Integer, List<Long>> comparisonsStats,
    		HashMap<Integer, List<Long>> assignmentStats, List<Long> normalSorterComparisonList, List<Long> normalSorterAssignmentList) {

    	final XYSeriesCollection dataSet = new XYSeriesCollection();
    	
    	//Compute the comparisons for normal quick sort.
    	long normalComparisonSum = 0, normalAssignmentSum = 0;
        for(Long i : normalSorterComparisonList)
        	normalComparisonSum += i;
        for(Long i : normalSorterAssignmentList)
        	normalAssignmentSum += i;       
        
        double avgForNormalSortComparisons = normalComparisonSum / (normalSorterComparisonList.size());
        double avgForNormalSortAssignments = normalAssignmentSum / (normalSorterAssignmentList.size());
        
    	XYSeries comparisonCountSeries = new XYSeries("Comparisons in Modified Quick Sorter");
    	XYSeries assignmentCountSeries = new XYSeries("Assignmenets in Modified Quick Sorter");
    	XYSeries normalSorterComparisonSeries = new XYSeries("Comparisons in Normal Quick Sorter");
    	XYSeries normalSorterAssignmentSeries = new XYSeries("Assignments in Normal Quick Sorter");
    	
    	long comparisonSum = 0, assignmentSum = 0;
    	
        for(Integer key : comparisonsStats.keySet()){
        	comparisonSum = 0;
        	assignmentSum = 0;
        	
        	for(Long count : comparisonsStats.get(key))
        		comparisonSum += count;
        	
        	for(Long count : assignmentStats.get(key))
        		assignmentSum += count;
        	
        	comparisonCountSeries.add((double)key, (comparisonSum / (comparisonsStats.get(key).size())));
        	assignmentCountSeries.add((double)key, (assignmentSum / (assignmentStats.get(key).size())));
        	normalSorterComparisonSeries.add((double)key, avgForNormalSortComparisons);
        	normalSorterAssignmentSeries.add((double)key, avgForNormalSortAssignments);
        }
        
        dataSet.addSeries(comparisonCountSeries);
        dataSet.addSeries(assignmentCountSeries);
        dataSet.addSeries(normalSorterComparisonSeries);
        dataSet.addSeries(normalSorterAssignmentSeries);
        
        return dataSet;
      
    }
    
    private JFreeChart createChart(XYDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createXYLineChart("Graphs of Comparisons", "Ins. sort Thresholds", 
        		"No. of Comparisons", dataset, PlotOrientation.VERTICAL, true, true, false
        );

        chart.setBackgroundPaint(Color.white);

        final XYPlot plot = chart.getXYPlot();
        
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);
        
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
                
        return chart;
        
    }

    public static void plotGraph(HashMap<Integer, List<Long>> comparisonsStats, HashMap<Integer, List<Long>> assignmentStats, 
    		List<Long> normalSortComparisonList,  List<Long> normalSorterAssignmentList, String title) {

        GraphPlotter graph = new GraphPlotter(comparisonsStats, assignmentStats, normalSortComparisonList, normalSorterAssignmentList, title);
        graph.pack();
        RefineryUtilities.centerFrameOnScreen(graph);
        graph.setVisible(true);        
        graph.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
}
