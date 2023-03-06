/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataowner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package org.jfree.chart.demo;

//package jfreeChart.examples;
//import functions.RealQuadraticMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A demo scatter plot.
 *
 * @author David Gilbert
 */
public class ScatterPlotDemo extends ApplicationFrame{

    /**
     * A demonstration application showing a scatter plot.
     *
     * @param title  the frame title.
     * @throws java.io.IOException
     */
    public ScatterPlotDemo(String title)
    {

        super(title);
        try {
            fun(title);
        } catch (IOException ex) {
            Logger.getLogger(ScatterPlotDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }
    public void fun(String title) throws IOException
         
    {
        JFreeChart chart = null; 
         XYDataset data = createDataset(title);
         if(title.equals("threekeyscorernew")||title.equals("twokeyscorernew"))
         {
          chart = ChartFactory.createScatterPlot(
            "Relevance Score Distribution with Cosidering distance between keywords",
            "Distance Between Keywords", "Relavance Score",
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
          );
         }
         
         if(title.equals("threekeyscorerold")||title.equals("twokeyscorerold"))
         {
          chart = ChartFactory.createScatterPlot(
            "Relevance Score Distribution without Cosidering distance between keywords",
            "Distance Between Keywords", "Relavance Score",
            data,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
          );
         }
        NumberAxis domainAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setHorizontalAxisTrace(true);
        //chartPanel.setVerticalZoom(true);
        //chartPanel.setHorizontalZoom(true);
        setContentPane(chartPanel);
        
        
        File imageFile = new File("D:\\KeywordGraphResult\\"+title+".png");
		int width = 640;
		int height = 480;
		
		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
        
            
        
        
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
       private static XYDataset createDataset(String fn) throws IOException{
        XYSeriesCollection dataset = new XYSeriesCollection();
        //RealQuadraticMap rqm = new RealQuadraticMap();
        //rqm.generateNumbers();
        XYSeries series = new XYSeries(fn);
                /*for(int i = 0; i < rqm.size(); i++){
                        series.add(rqm.yValueAt(i), rqm.xValueAt(i));
                }*/
        
         
         BufferedReader readresult=null;
                String line="";
                
                try
                {
                    readresult = new BufferedReader(new FileReader(new File("D:\\KeywordGraphResult\\"+fn+".txt")));
                }
                catch(FileNotFoundException e)
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
                    readresult = new BufferedReader(new FileReader(new File("D:\\KeywordGraphResult\\"+fn+".txt")));
                }
                catch(FileNotFoundException e)
                {
                    System.out.println("could not open file");
                    System.out.println("here2");
                   // System.exit(1);
                }
                Double []oldr=new Double[N];
                Double []newr=new Double[N];
                line=readresult.readLine();
                int i=0;
                while(line!=null)
                {    
                 StringTokenizer tokens=new StringTokenizer(line," ");
                 oldr[i]=Double.parseDouble(tokens.nextToken());
                 newr[i]=Double.parseDouble(tokens.nextToken());
                 i++;
                 line=readresult.readLine();
                }
                
                readresult.close();
                
                Double tmp=0.0,t=0.0;
                
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
		 series.add(oldr[i],newr[i]);
                }
		
                dataset.addSeries(series);
                return dataset;
    }
   

}

