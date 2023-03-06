/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataowner;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.jfree.ui.RefineryUtilities;

public class ScoreDistribution extends JFrame implements ActionListener
{
    
    Label l1,l2;
    JButton twold,twonew,thold,thnew;
    String str=""; 
    ScatterPlotDemo demo;  
        
    public ScoreDistribution()
    {
        
          
          super("*******Cloud Software******");
         
          
          l1=new Label("Advanced Relevance Ranking Score Result For Multi-Keyword Search");
          twold=new JButton("Two Key Old Eqn Score");
          twonew=new JButton("Two Key New Eqn Score");
          thold=new JButton("Three Key Old Eqn Score");
          thnew=new JButton("Three Key New Eqn Score");
                // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLayout(null);
          l1.setLocation(20, 20);
          l1.setSize(700, 50);
          twold.setBounds(75,150,250,50);
          twonew.setBounds(355, 150, 250,50);
          thold.setBounds(75, 250,250,50);
          thnew.setBounds(355, 250,250, 50);
         
          twold.addActionListener(this);
          twonew.addActionListener(this);
          thold.addActionListener(this);
          thnew.addActionListener(this);
         
           add(l1);
           add(twold);
           add(twonew);
           add(thold);
           add(thnew);
                                  
}

    
   
   
    public void actionPerformed(ActionEvent e)
    {
        
       
        if (e.getActionCommand().equals("Two Key Old Eqn Score"))
        {
            demo= new ScatterPlotDemo("twokeyscorerold");
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
             demo.setResizable(true);
             System.out.println("out of twokeyold");
             
        }
        
        if (e.getActionCommand().equals("Two Key New Eqn Score"))
        {
            demo= new ScatterPlotDemo("twokeyscorernew");
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
            demo.setResizable(true);
           System.out.println("out of twokeynew");
        }
         
        if (e.getActionCommand().equals("Three Key Old Eqn Score"))
        {
            demo= new ScatterPlotDemo("threekeyscorerold");
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
             demo.setResizable(true);
          System.out.println("out of threekeyold");
        }
        if (e.getActionCommand().equals("Three Key New Eqn Score"))
        {
            demo= new ScatterPlotDemo("threekeyscorernew");
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
             demo.setResizable(true);
         System.out.println("out of threekeynew");
        }
    }
    
}