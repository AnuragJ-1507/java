/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataowner;
//package net.codejava.graphics.jfreechart;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * This program demonstrates how to draw XY line chart with XYDataset
 * using JFreechart library.
 * @author www.codejava.net
 *
 */
public class XYLineChartExample extends JFrame {

	public XYLineChartExample() throws IOException {
		super("XY Line Chart for Number of Distinct Keywords Comparision");
		
		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(640, 480);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private JPanel createChartPanel() throws IOException {
		String chartTitle = "Number of Distinct Keywords Comparision";
		String xAxisLabel = "File Length";
		String yAxisLabel = "Number of Distinct Keywords";
		
		XYDataset dataset = createDataset();
		
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,xAxisLabel, yAxisLabel, dataset);
		

		
		customizeChart(chart);
		
		// saves the chart as an image files
		File imageFile = new File("D:\\KeywordGraphResult\\XYLineChart.png");
		int width = 640;
		int height = 480;
		
		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		return new ChartPanel(chart);
	}

	private XYDataset createDataset() throws IOException {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("Base Paper Distinct keyword Algorithm");
		XYSeries series2 = new XYSeries("Enhanced with IR techniques Distinct keyword Algorithm");
		
		//file length , no of distict keywords
                BufferedReader readresult=null;
                String line="";
                
                try
                {
                    readresult = new BufferedReader(new FileReader(new File("D:\\KeywordGraphResult\\result.txt")));
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                    System.out.println("here2");
                   // System.exit(1);
                }
                int N=0;
                line=readresult.readLine();
                while(line!=null)
                {    
                 N++;   
                 line=readresult.readLine();
                }
                readresult.close();
                
                
                try
                {
                    readresult = new BufferedReader(new FileReader(new File("D:\\KeywordGraphResult\\result.txt")));
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                    System.out.println("here2");
                   // System.exit(1);
                }
                Integer []oldr=new Integer[N];
                Integer []newr=new Integer[N];
                line=readresult.readLine();
                int i=0;
                while(line!=null)
                {    
                 StringTokenizer tokens=new StringTokenizer(line," ");
                 oldr[i]=Integer.parseInt(tokens.nextToken());
                 newr[i]=Integer.parseInt(tokens.nextToken());
                 i++;
                 line=readresult.readLine();
                }
                
                readresult.close();
                
                Integer tmp=0,t=0;
                
                System.out.println(oldr.length);
                 for(i=0;i<oldr.length;i++)
                 {
                    for(int j=0;j<oldr.length-1;j++)
                    {
                        if(oldr[j]>oldr[j+1])
                        {
                            tmp=oldr[j];
                            t=newr[j];

                            oldr[j]=oldr[j+1];
                            newr[j]=newr[j+1];
                            
                            oldr[j+1]=tmp;
                            newr[j+1]=t;
                        }

                    }
                }
                
                
                
                for(i=0;i<oldr.length;i++)
                {
		 series1.add(oldr[i],oldr[i]);
                }
		for(i=0;i<newr.length;i++)
                {
		 series2.add(oldr[i],newr[i]);
                }
		
				
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		
		
		return dataset;
	}
	
	private void customizeChart(JFreeChart chart) {
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		
		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		
		
		// sets paint color for plot outlines
		plot.setOutlinePaint(Color.BLUE);
		plot.setOutlineStroke(new BasicStroke(2.0f));
		
		// sets renderer for lines
		plot.setRenderer(renderer);
		
		// sets plot background
		plot.setBackgroundPaint(Color.DARK_GRAY);
		
		// sets paint color for the grid lines
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);
		
	}
	
	
}