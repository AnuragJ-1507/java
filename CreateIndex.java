
package dataowner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;


public class CreateIndex
{
    BufferedReader reader=null;
    //private String fileContent;
    BufferedWriter br=null;
    BufferedReader readern=null;
    BufferedReader readerf=null,readerc=null,readerd=null;
    BufferedReader br1=null,br2=null;
    BufferedReader keydist=null;
    BufferedReader onlywords=null;
    BufferedWriter brk=null,brold=null;
    BufferedWriter tkscorero=null,tkscorern=null,thkscorero=null,thkscorern=null;
    int N=0;
    BufferedWriter wkinf=null;
    public CreateIndex() throws Exception 
    {
            
                  
    }
    public void getNext(File path) throws Exception
	{
               
		File pathlist[]=path.listFiles();
		for(int i=0;i<pathlist.length;i++)
		{
			if(pathlist[i].isDirectory())
			{
                              System.out.println("here3");
			      N=findoneKeyOcc(pathlist[i]);                               
                              findtwoKeyOcc(pathlist[i],N);
                              findthreeKeyOcc(pathlist[i],N); 
                           // getNext(pathlist[i]);
			}
			else
			{
			             
			}
		}
                brold.close();
	}
    public void getFile(File fs) throws Exception
	{
	        System.out.println("here2");
                boolean s=new File("D:\\scoreindexfiles\\oldindex").mkdirs();
                try
                {    
                    brold= new BufferedWriter(new FileWriter(new File("D:\\scoreindexfiles\\oldindex\\oldindexscore.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file br");
                   // System.exit(1);
                }
                int i=1;
	        while(i>0)
		{		
			//fos.write(("<Category name=\""+fs.getName()+"\" path=\""+fs.getPath()+"\">").getBytes());
			getNext(fs);	
                        i--;
		}
                brold.close();
        }
    public int findoneKeyOcc(File f) throws IOException
    {
               String dmn=f.getName();
               /*StringTokenizer tok=new StringTokenizer(dmn,":\\");
                 tok.nextToken();
                 tok.nextToken();
                 dmn=tok.nextToken();*/
                boolean s=new File("D:\\scoreindexfiles\\"+dmn).mkdir();
               String word="";
                try
                {    
                    br= new BufferedWriter(new FileWriter(new File("D:\\scoreindexfiles\\"+dmn+"\\onekey.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }
                //----------------------------------------------------------------------------------------------------------------------------------
                try
                {
                    readern = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\newresult.txt")));
                }
                catch(Exception e)
                {
                   System.exit(1);
                }
               String l=readern.readLine();
               String temp="";
               while(l!=null)
               {
                 temp=l;  
                 l=readern.readLine();
               }
               l=temp;
               System.out.println(l+" i am here");   
               StringTokenizer tokens = new StringTokenizer(l," ");
                word=tokens.nextToken();
                    System.out.println(word+" i am here for");
                 Double N=new Double(word);
                 System.out.println("Total files="+N);
                 readern.close();
                 //----------------------------------------------------------------------------------------------------------------------------------
                
               
                 //----------------------------------------------------------------------------------------------------------------------------------
                
                try
                {    
                    br1= new BufferedReader(new FileReader(new File("D:\\indexfiles\\"+dmn+"\\onekeyinnooffile.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }	
               String line=br1.readLine();
               temp="";
               
               while(line!=null)
               {
                 temp=line;  
                 StringTokenizer token = new StringTokenizer(line," ");
                  System.out.println(token.countTokens());
                 String stn=token.nextToken();
                 br.write(stn+" ");
                 brold.write(stn+" ");
                 String keyw=stn;
                 
                 int i=0;
                 System.out.println(token.countTokens());
                 Double score[]=new Double[token.countTokens()-1];
                 for(int t=0;t<score.length;t++) 
                         score[t]=0.0;
                 String files[]= new String[token.countTokens()-1];
                  for(int t=0;t<files.length;t++) 
                         files[t]="";
                 System.out.println("here1 "+keyw);
                 
                 while(token.hasMoreTokens()) 
                  {
                      
                      Double fd=0.0;
                      Double fdt=0.0;
                      Double ft=0.0;
                      stn=token.nextToken();
                      System.out.println("here0 "+stn);
                      
                      if(stn.endsWith(".txt"))
                      {
                          files[i]=stn;
                          
                          
                                 //file length======================================================================================================
                                   try
                                    {
                                        readerf = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\newresultfile.txt")));
                                    }
                                    catch(Exception e)
                                    {
                                        System.exit(1);
                                    }
                                    String lf=readerf.readLine();
                                    //String temp="";
                                   while(lf!=null)
                                   {
                                       
                                        StringTokenizer tokensf = new StringTokenizer(lf," ");
                                        word=tokensf.nextToken();
                                        System.out.println("here1");
                                        if(files[i].equals(word))
                                        {
                                          fd=Double.parseDouble(tokensf.nextToken().toString());
                                          System.out.println("here2");
                                          break;
                                        }
                                        tokensf.nextToken();
                                        lf=readerf.readLine();
                                        System.out.println("here3");
                                    }
                                    readerf.close();
                                    System.out.println("here-a");
                                   //end file length======================================================================================================
                                           
                                    
                                    try
                                     {
                                        readerc = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\wordcount.txt")));
                                    }
                                    catch(Exception e)
                                    {
                                        System.exit(1);
                                    }
                                    lf=readerc.readLine();
                                    System.out.println("here-b");
                                    //String temp="";
                                    L:  while(lf!=null)
                                    {
                                        while(!lf.endsWith(".txt")&&!files[i].equals(lf))
                                        {
                                            lf=readerc.readLine();
                                            System.out.println("here4");
                                            if(lf==null)
                                                break L;
                                        }
                                        
                                        lf=readerc.readLine();
                                        //StringTokenizer tokenst=null;
                                        StringTokenizer tokensf = new StringTokenizer(lf," ");
                                        word=tokensf.nextToken();
                                        
                                        while(!word.equals(keyw))
                                        {
                                            lf=readerc.readLine();
                                            tokensf = new StringTokenizer(lf," ");
                                            word=tokensf.nextToken();
                                            System.out.println("here-c"+lf+" "+word);
                                            System.out.println("here-d");
                                        }
                                        if(keyw.equals(word))
                                        {
                                          fdt=Double.parseDouble(tokensf.nextToken().toString());
                                          System.out.println("here5");
                                          break;
                                        }
                                        tokensf.nextToken();
                                        //lf=readerc.readLine();
                                    }
                                    readerc.close();
                                    System.out.println("here-e");
                                    //======================================================================================
                                    try
                                     {
                                        readerd = new BufferedReader(new FileReader(new File("D:\\indexfiles\\"+dmn+"\\onekeycount.txt")));
                                     }
                                    catch(Exception e)
                                    {
                                        System.exit(1);
                                    }
                                    lf=readerd.readLine();
                                    
                                    //String temp="";
                                    L1: while(lf!=null)
                                   {
                                        while(!lf.endsWith(".txt")&&!files[i].equals(lf))
                                        {
                                            lf=readerd.readLine();
                                            System.out.println("here6");
                                            if(lf==null)
                                                break L1;
                                        }
                                        lf=readerd.readLine();
                                        StringTokenizer tokenst=null;
                                        StringTokenizer tokensf = new StringTokenizer(lf," ");
                                        word=tokensf.nextToken();
                                        while(!word.equals(keyw))
                                        {
                                            lf=readerd.readLine();
                                            tokensf = new StringTokenizer(lf," ");
                                            word=tokensf.nextToken();
                                        }
                                        if(keyw.equals(word))
                                        {
                                          ft=Double.parseDouble(tokensf.nextToken().toString());
                                          System.out.println("here5");
                                          break;
                                        }
                                        tokensf.nextToken();
                                        System.out.println("here7");
                                    }
                                    readerd.close();
                                    
                                    
                                    
                                    
                          score[i]=(1/fd)*(1+Math.log(fdt))*Math.log(1+(N/ft));
                          System.out.println(fd+" "+fdt+" "+ft+"="+score[i]);
                          System.out.println("here8");
                      }
                      
                  i++;
                  }
                 System.out.println("here9");
                 Double tmp=0.0;
                 String t;
                   t = "";
                   System.out.println(score.length);
                 for(i=0;i<score.length;i++)
                 {
                    for(int j=0;j<score.length-1;j++)
                    {
                        if(score[j]<score[j+1])
                        {
                            tmp=score[j];
                            t=files[j];

                            score[j]=score[j+1];
                            files[j]=files[j+1];
                            
                            score[j+1]=tmp;
                            files[j+1]=t;
                        }

                    }
                }
                 for(i=0;i<score.length;i++)
                 {
                     br.write(files[i]+" ");
                     brold.write(files[i]+" ");
                     br.write(score[i].toString()+" ");
                     brold.write(score[i].toString()+" ");
                 }
                 br.newLine();
                 brold.newLine();
              line=br1.readLine();   
              System.out.println("end");
         }
               br.close();
               br1.close();
               System.out.println("last end");
               return N.intValue();
    }
    public void findtwoKeyOcc(File f,int N) throws IOException
    {
               String dmn=f.getName();
                boolean s=new File("D:\\scoreindexfiles\\"+dmn).mkdir();
               String word="";
                try
                {    
                    br= new BufferedWriter(new FileWriter(new File("D:\\scoreindexfiles\\"+dmn+"\\twokey.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }
                //----------------------------------------------------------------------------------------------------------------------------------
                //----------------------------------------------------------------------------------------------------------------------------------
                
                try
                {    
                    tkscorero= new BufferedWriter(new FileWriter(new File("D:\\KeywordGraphResult\\twokeyscorerold.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }
                try
                {    
                    tkscorern= new BufferedWriter(new FileWriter(new File("D:\\KeywordGraphResult\\twokeyscorernew.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }
                                         
                 //----------------------------------------------------------------------------------------------------------------------------------
                
                try
                {    
                    br1= new BufferedReader(new FileReader(new File("D:\\indexfiles\\"+dmn+"\\twokeyinnooffile.txt")));
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }	
                try
                {    
                    br2= new BufferedReader(new FileReader(new File("D:\\indexfiles\\"+dmn+"\\onlytwokeycount.txt")));
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }
               String line=br1.readLine();
               String linec=br2.readLine();
               String temp="",tempc="";
               while(line!=null)
               {
                 temp=line;  
                 tempc=linec;
                 StringTokenizer token = new StringTokenizer(line," ");
                 StringTokenizer tokenc = new StringTokenizer(linec," ");
                  System.out.println(token.countTokens());
                 String stn=token.nextToken();
                   String stnc=tokenc.nextToken();
                 br.write(stn+" ");
                 brold.write(stn+" ");
                 String keyw=stn;
                 
                 int i=0;
                 System.out.println(token.countTokens());
                 Double score[]=new Double[token.countTokens()-1];
                 for(int t=0;t<score.length;t++) 
                         score[t]=0.0;
                 Double scoreold[]=new Double[token.countTokens()-1];
                 for(int t=0;t<scoreold.length;t++) 
                         scoreold[t]=0.0;
                 String files[]= new String[token.countTokens()-1];
                  for(int t=0;t<files.length;t++) 
                         files[t]="";
                  String filesold[]= new String[token.countTokens()-1];
                  for(int t=0;t<filesold.length;t++) 
                         filesold[t]="";
                 System.out.println("here1 "+keyw);
                 Double ft=Double.parseDouble(tokenc.nextToken());
                 while(token.hasMoreTokens()) 
                  {
                      Double fd=0.0;
                      Double fdt=0.0;
                      Double dist=0.0;
                      stn=token.nextToken();
                      System.out.println("here0 "+stn);
                      StringTokenizer tokenf = new StringTokenizer(stn,"-");
                      String filename=tokenf.nextToken();
                      if(filename.endsWith(".txt"))
                      {              
                          
                          System.out.println(filename);
                          files[i]=filename;
                          filesold[i]=filename;
                          dist=Double.parseDouble(tokenf.nextToken());
                          fdt=Double.parseDouble(tokenf.nextToken());
                                                               
                                    //file length======================================================================================================
                                   try
                                    {
                                        readerf = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\newresultfile.txt")));
                                    }
                                    catch(Exception e)
                                    {
                                        System.exit(1);
                                    }
                                    String lf=readerf.readLine();
                                    //String temp="";
                                   while(lf!=null)
                                   {
                                       
                                        StringTokenizer tokensf = new StringTokenizer(lf," ");
                                        word=tokensf.nextToken();
                                        System.out.println("here1");
                                        if(files[i].equals(word))
                                        {
                                          fd=Double.parseDouble(tokensf.nextToken().toString());
                                          System.out.println("here2");
                                          break;
                                        }
                                        tokensf.nextToken();
                                        lf=readerf.readLine();
                                        System.out.println("here3");
                                    }
                                    readerf.close();
                                    System.out.println("here-a");
                                   //end file length======================================================================================================
                                           
                                    
                          tkscorero.write(dist+" "+((1/fd)*(1+Math.log(fdt))*(1+Math.log(N/ft)))+"");  
                          tkscorero.newLine();
                          score[i]=(1/fd)*(1+Math.log(fdt))*(1+Math.log(N/ft))*(2/Math.log(dist));
                          scoreold[i]=(1/fd)*(1+Math.log(fdt))*(1+Math.log(N/ft));
                          System.out.println(fd+" "+fdt+" "+ft+" "+dist+"="+score[i]);
                          System.out.println(fd+" "+fdt+" "+ft+"="+scoreold[i]);
                          tkscorern.write(dist+" "+score[i]);
                          tkscorern.newLine();
                          System.out.println("here8");
                      }
                      
                  i++;
                 }
                 System.out.println("here9");
                 Double tmp=0.0;
                 String t;
                   t = "";
                   Double tmpold=0.0;
                 String told;
                   told = "";
                   System.out.println(score.length);
                 for(i=0;i<score.length;i++)
                 {
                    for(int j=0;j<score.length-1;j++)
                    {
                        if(score[j]<score[j+1])
                        {
                            tmp=score[j];
                            t=files[j];

                            score[j]=score[j+1];
                            files[j]=files[j+1];
                            
                            score[j+1]=tmp;
                            files[j+1]=t;
                        }

                    }
                }
               /////////////////////////////////////////////////////////////////////////////////////////////////  
                 for(i=0;i<scoreold.length;i++)
                 {
                    for(int j=0;j<scoreold.length-1;j++)
                    {
                        if(scoreold[j]<scoreold[j+1])
                        {
                            tmpold=scoreold[j];
                            told=filesold[j];

                            scoreold[j]=scoreold[j+1];
                            filesold[j]=filesold[j+1];
                            
                            scoreold[j+1]=tmpold;
                            filesold[j+1]=told;
                        }

                    }
                }
                 
                 
                 for(i=0;i<score.length;i++)
                 {
                     br.write(files[i]+" ");
                     br.write(score[i].toString()+" ");
                 }
                 
                 for(i=0;i<scoreold.length;i++)
                 {
                     brold.write(filesold[i]+" ");
                     brold.write(scoreold[i].toString()+" ");
                 }
                 
                 
                 br.newLine();
                 brold.newLine();
              line=br1.readLine();   
              linec=br2.readLine();
              System.out.println("end");
         }
               br.close();
               br1.close();
               br2.close();
               tkscorero.close();
               tkscorern.close();
               System.out.println("last end");
               //return N.intValue();
    }
    public void findthreeKeyOcc(File f,int N) throws IOException
    {
               String dmn=f.getName();
                boolean s=new File("D:\\scoreindexfiles\\"+dmn).mkdir();
               String word="";
                try
                {    
                    br= new BufferedWriter(new FileWriter(new File("D:\\scoreindexfiles\\"+dmn+"\\threekey.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }
                //----------------------------------------------------------------------------------------------------------------------------------
                //----------------------------------------------------------------------------------------------------------------------------------
                 try
                {    
                    thkscorero= new BufferedWriter(new FileWriter(new File("D:\\KeywordGraphResult\\threekeyscorerold.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }
                try
                {    
                    thkscorern= new BufferedWriter(new FileWriter(new File("D:\\KeywordGraphResult\\threekeyscorernew.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }         
                 //----------------------------------------------------------------------------------------------------------------------------------
                
                try
                {    
                    br1= new BufferedReader(new FileReader(new File("D:\\indexfiles\\"+dmn+"\\threekeyinnooffile.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }	
                try
                {    
                    br2= new BufferedReader(new FileReader(new File("D:\\indexfiles\\"+dmn+"\\onlythreekeycount.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }
               String line=br1.readLine();
               String linec=br2.readLine();
               String temp="",tempc="";
               
               while(line!=null)
               {
                 temp=line;  
                 tempc=linec;
                 StringTokenizer token = new StringTokenizer(line," ");
                 StringTokenizer tokenc = new StringTokenizer(linec," ");
                  System.out.println(token.countTokens());
                 String stn=token.nextToken();
                   String stnc=tokenc.nextToken();
                 br.write(stn+" ");
                 brold.write(stn+" ");
                 String keyw=stn;
                 
                 int i=0;
                 System.out.println(token.countTokens());
                 Double score[]=new Double[token.countTokens()-1];
                 for(int t=0;t<score.length;t++) 
                         score[t]=0.0;
                  Double scoreold[]=new Double[token.countTokens()-1];
                 for(int t=0;t<scoreold.length;t++) 
                         scoreold[t]=0.0;
                 String files[]= new String[token.countTokens()-1];
                  for(int t=0;t<files.length;t++) 
                         files[t]="";
                  String filesold[]= new String[token.countTokens()-1];
                  for(int t=0;t<filesold.length;t++) 
                         filesold[t]="";
                 System.out.println("here1 "+keyw);
                 Double ft=Double.parseDouble(tokenc.nextToken());
                 while(token.hasMoreTokens()) 
                  {
                      
                      Double fd=0.0;
                      Double fdt=0.0;
                      Double dist=0.0;
                      stn=token.nextToken();
                      System.out.println("here0 "+stn);
                      StringTokenizer tokenf = new StringTokenizer(stn,"-");
                      String filename=tokenf.nextToken();
                      if(filename.endsWith(".txt"))
                      {              
                          
                          files[i]=filename;
                          filesold[i]=filename;
                          dist=Double.parseDouble(tokenf.nextToken());
                          fdt=Double.parseDouble(tokenf.nextToken());
                                                               
                                   //file length======================================================================================================
                                   try
                                    {
                                        readerf = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\newresultfile.txt")));
                                    }
                                    catch(Exception e)
                                    {
                                        System.exit(1);
                                    }
                                    String lf=readerf.readLine();
                                    //String temp="";
                                   while(lf!=null)
                                   {
                                       
                                        StringTokenizer tokensf = new StringTokenizer(lf," ");
                                        word=tokensf.nextToken();
                                        System.out.println("here1");
                                        if(files[i].equals(word))
                                        {
                                          fd=Double.parseDouble(tokensf.nextToken().toString());
                                          System.out.println("here2");
                                          break;
                                        }
                                        tokensf.nextToken();
                                        lf=readerf.readLine();
                                        System.out.println("here3");
                                    }
                                    readerf.close();
                                    System.out.println("here-a");
                                   //end file length======================================================================================================
                                           
                                    
                          thkscorero.write(dist+" "+((1/fd)*(1+Math.log(fdt))*(1+Math.log(N/ft)))+""); 
                          thkscorero.newLine();
                          score[i]=(1/fd)*(1+Math.log(fdt))*(1+Math.log(N/ft))*(3/Math.log(dist));
                          scoreold[i]=(1/fd)*(1+Math.log(fdt))*(1+Math.log(N/ft));
                          System.out.println(fd+" "+fdt+" "+ft+" "+dist+"="+score[i]);
                           System.out.println(fd+" "+fdt+" "+ft+"="+scoreold[i]);
                          thkscorern.write(dist+" "+score[i]);
                          thkscorern.newLine();
                          System.out.println("here8");
                      }
                      
                  i++;
                  }
                 System.out.println("here9");
                 Double tmp=0.0;
                 String t;
                   t = "";
                   Double tmpold=0.0;
                 String told;
                   told = "";
                   System.out.println(score.length);
                 for(i=0;i<score.length;i++)
                 {
                    for(int j=0;j<score.length-1;j++)
                    {
                        if(score[j]<score[j+1])
                        {
                            tmp=score[j];
                            t=files[j];

                            score[j]=score[j+1];
                            files[j]=files[j+1];
                            
                            score[j+1]=tmp;
                            files[j+1]=t;
                        }

                    }
                }
                 
                 for(i=0;i<scoreold.length;i++)
                 {
                    for(int j=0;j<scoreold.length-1;j++)
                    {
                        if(scoreold[j]<scoreold[j+1])
                        {
                            tmpold=scoreold[j];
                            told=filesold[j];

                            scoreold[j]=scoreold[j+1];
                            filesold[j]=filesold[j+1];
                            
                            scoreold[j+1]=tmpold;
                            filesold[j+1]=told;
                        }

                    }
                }
                 
                 
                 for(i=0;i<score.length;i++)
                 {
                     br.write(files[i]+" ");
                     br.write(score[i].toString()+" ");
                 }
                 
                 for(i=0;i<scoreold.length;i++)
                 {
                     brold.write(filesold[i]+" ");
                     brold.write(scoreold[i].toString()+" ");
                 }
              br.newLine();
              brold.newLine();
              line=br1.readLine();   
              linec=br2.readLine();
              System.out.println("end");
         }
               br.close();
               br1.close();
               br2.close();
               thkscorero.close();
               thkscorern.close();
               System.out.println("last end three");
               //return N.intValue();
    }
    
}
