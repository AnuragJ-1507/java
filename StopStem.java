/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataowner;
import java.io.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StopStem
{
	private Porter porter;
        BufferedReader reader = null;
        StopStem stopStem;
	private java.util.HashSet stopWords;
	public boolean isStopWord(String str)
	{
		return stopWords.contains(str);	
	}
	public StopStem(String str) throws IOException
	{
		super();
                //stopStem = this;
		porter = new Porter();
		stopWords = new java.util.HashSet();
		//System.out.println("1");
                
                try
                {
                    reader = new BufferedReader(new FileReader(new File(str)));
                }
                catch(Exception e)
                {
                    //System.out.println("could not open file");
                    //System.exit(1);
                }
                String l = reader.readLine();
                while( l != null)
                {
                    stopWords.add(l);   
                    //read next line
                    l = reader.readLine();
                }
               
               reader.close();                    
               //System.out.println(stopWords);
	}
	public String stem(String str)
	{
		return porter.stripAffixes(str);
	}
	 public String start(String arg)
	{
		
                stopStem = this;
                String input="";
                String str="";
                     //System.out.print("Please enter a single English word: ");
                    //BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                    input = arg.toLowerCase();
                    if(input.length()>0)
                    {
                        if (stopStem.isStopWord(input))
                            return "";//System.out.println("It should be stopped");
                        else
                        { str=stopStem.stem(input);
                            //str=input;
                          //System.out.println(str);
                        }
                    }
                
                return str;
	}
}
