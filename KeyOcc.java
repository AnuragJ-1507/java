    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

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


    public class KeyOcc
    {
        BufferedReader reader=null;
        //private String fileContent;
        BufferedWriter br=null,br1=null;
        BufferedWriter otwkc=null,otkc=null;
        BufferedReader readern=null;
        BufferedReader keydist=null;
        BufferedReader onlywords=null,wordc;
        BufferedWriter brk=null;
        int N=0;
        BufferedWriter wkinf=null;
        public KeyOcc() throws Exception 
        {

        }
        public void getNext(File path) throws Exception
            {

                    File pathlist[]=path.listFiles();
                    for(int i=0;i<pathlist.length;i++)
                    {
                            if(pathlist[i].isDirectory())
                            {
                                    N=findoneKeyOcc(pathlist[i]);                               
                                  findtwoKeyOcc(pathlist[i],N);
                                   findthreeKeyOcc(pathlist[i],N); 
                               // getNext(pathlist[i]);
                            }
                            else
                            {

                            }
                    }
            }
        public void getFile(File fs) throws Exception
            {

                    int i=1;
                    while(i>0)
                    {		
                            //fos.write(("<Category name=\""+fs.getName()+"\" path=\""+fs.getPath()+"\">").getBytes());
                            getNext(fs);	
                            i--;
                    }
            }
        public int findoneKeyOcc(File f) throws IOException
        {
                   String dmn=f.getName();
                    boolean s=new File("D:\\indexfiles\\"+dmn).mkdir();
                   String word="";
                    try
                    {    
                        br= new BufferedWriter(new FileWriter(new File("D:\\indexfiles\\"+dmn+"\\onekeyinnooffile.txt")));

                    }
                    catch(IOException e)
                    {
                        System.out.println("could not open file");
                       // System.exit(1);
                    }	
                    try
                    {    
                        br1= new BufferedWriter(new FileWriter(new File("D:\\indexfiles\\"+dmn+"\\onekeycount.txt")));

                    }
                    catch(IOException e)
                    {
                        System.out.println("could not open file");
                       // System.exit(1);
                    }	
                    /* try
                    {    
                        brk= new BufferedWriter(new FileWriter(new File("D:\\indexfiles\\keyinnofile.txt")));

                    }
                    catch(IOException e)
                    {
                        System.out.println("could not open file");
                       // System.exit(1);
                    }*/	
                    try
                    {
                        reader = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\onlywords.txt")));
                    }
                    catch(Exception e)
                    {
                       System.exit(1);
                    }
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
                     Integer N=new Integer(word);
                     System.out.println("Total files="+N);
                    String filecontent[]=new String[N.intValue()];
                    for(int t=0;t<N.intValue();t++)
                    {
                     filecontent[t]="";
                    }
                    String filecontent1="";
                    int i=-1;
                    readern.close();
                    String st=reader.readLine();
                     while(st!=null)
                     {
                          if(st.endsWith(".txt"))
                                  i++;
                            filecontent[i]+=st+" ";
                            filecontent1+=st+" ";
                            System.out.println(i+" "+st);
                          st=reader.readLine();
                     }
                     String fn="";
                     for(int j=0;j<N;j++)
                     {
                            // split the fileContent with space delimiter
                      StringTokenizer tokenizer=new StringTokenizer(filecontent[j]," ");
                      while(tokenizer.hasMoreTokens()) 
                      {
                          int count=0;
                         String stn=tokenizer.nextToken();
                         if(stn.endsWith(".txt")) 
                         {
                             fn=stn;
                             br1.write(stn);
                             br1.newLine();
                             continue;
                         }    
                         else
                         {
                           StringTokenizer tokenizer1=new StringTokenizer(filecontent1," ");
                           br.write(stn+" ");
                           br1.write(stn+" ");
                           //brk.write(stn+" ");
                           System.out.print(stn+" ");
                           String stf="";
                           while(tokenizer1.hasMoreTokens()) 
                            {
                             // it is a HashSet word
                             String str=tokenizer1.nextToken();
                             if(str.endsWith(".txt"))
                             {    
                                stf=str;
                                continue;
                             }
                             else if(str.equals(stn))
                             {
                              count++;
                              br.write(stf+" ");
                              System.out.print(stf+" ");
                             }                  
                           }
                         }
                           br.write(count+"");
                           br1.write(count+"");
                          // brk.write(count+"");
                           System.out.print(count);
                           System.out.println();
                           br.newLine();
                           br1.newLine();
                          // brk.newLine();
                      }
                     }
                     br.close();
                     br1.close();
                    // brk.close();
                     reader.close();
                     return N;
                    // readern.close(
        }
        public void findtwoKeyOcc(File f,int N) throws IOException
        {
            String dmn=f.getName();
            boolean s=new File("D:\\indexfiles\\"+dmn).mkdirs();   
                    
                    String word="";
                    try
                    {    
                        wkinf= new BufferedWriter(new FileWriter(new File("D:\\indexfiles\\"+dmn+"\\twokeyinnooffile.txt")));

                    }
                    catch(IOException e)
                    {
                        System.out.println("could not open file");
                       // System.exit(1);
                    }
                    try
                    {    
                        otwkc= new BufferedWriter(new FileWriter(new File("D:\\indexfiles\\"+dmn+"\\onlytwokeycount.txt")));

                    }
                    catch(IOException e)
                    {
                        System.out.println("could not open file");
                       // System.exit(1);
                    }

                    try
                    {
                        onlywords = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\onlywords.txt")));
                    }
                    catch(Exception e)
                    {
                       System.exit(1);
                    }
                    
                    String filecontent[]=new String[N];
                    for(int t=0;t<N;t++)
                        filecontent[t]="";
                    String filecontent1="";
                    int i=-1;
                    //readern.close();
                    String st=onlywords.readLine();
                     while(st!=null)
                     {
                          if(st.endsWith(".txt"))
                                  i++;
                            filecontent[i]+=st+" ";
                            filecontent1+=st+" ";
                            System.out.println(i+" "+st);
                            st=onlywords.readLine();
                     }
                     String fn="";
                     String stn2="";
                     String stn="";
                     String str="";
                     String str2="";
                     String kyd="",kyc="",kyc1="",kyc2="";
                     for(int j=0;j<N;j++)
                     {

                      int flag=0;// split the fileContent with space delimiter
                      StringTokenizer tokenizer=new StringTokenizer(filecontent[j]," ");
                         while(tokenizer.hasMoreTokens()) 
                        {
                          int count=0;
                          int flag1=0;
                          try
                          {    
                            keydist= new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\keydist.txt")));
                          }
                          catch(IOException e)
                          {
                            System.out.println("could not open file");
                            // System.exit(1);
                          }
                          
                                       
                          if(flag==0)
                          {     
                            stn=tokenizer.nextToken();
                            flag=1;
                          }
                          else
                          {
                            stn=stn2;
                          }  
                          stn2=tokenizer.nextToken();

                         if(stn.endsWith(".txt")) 
                         {
                             fn=stn;
                             System.out.println(fn);
                             continue;
                         }    
                         else
                         {
                             int c=0;
                           stn=stn+","+stn2;
                           
                           try
                          {
                             wordc = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\wordcount.txt")));
                          }
                           catch(Exception e)
                            {
                              System.out.println("could not open file");
                              System.exit(1);
                            }
                           StringTokenizer tokenizer1=new StringTokenizer(filecontent1," ");
                            
                           wkinf.write(stn+" ");
                           otwkc.write(stn+" ");
                           //.write(stn+" ");
                           System.out.print(stn+" ");
                           String stf="";
                           while(tokenizer1.hasMoreTokens()) 
                            {
                             // it is a HashSet word
                            if(flag1==0)
                            {     
                              str=tokenizer1.nextToken();
                              kyc=wordc.readLine();
                              flag1=1;
                            }
                            else
                            {
                             str=str2;
                             kyc=kyc2;
                            }   
                             kyd=keydist.readLine();
                             kyc2=wordc.readLine();
                             str2=tokenizer1.nextToken();
                             if(str.endsWith(".txt"))
                             {    
                                stf=str;
                                 System.out.print(stf);
                                continue;
                             }
                             else
                             {
                              //str2=tokenizer1.nextToken();
                              StringTokenizer tokenizer3=new StringTokenizer(kyd," ");
                              String temp1=tokenizer3.nextToken();
                              String temp2=tokenizer3.nextToken();
                              
                              
                              str=str+","+str2;
                              if(str.equals(stn))
                             {
                                 
                              StringTokenizer tokenizer4=new StringTokenizer(kyc," ");
                              String temp41=tokenizer4.nextToken();
                              String temp42=tokenizer4.nextToken();
                              System.out.println(temp41+" "+temp42);
                              StringTokenizer tokenizer5=new StringTokenizer(kyc2," ");
                              String temp51=tokenizer5.nextToken();
                              String temp52=tokenizer5.nextToken();
                              System.out.println(temp51+" "+temp52);
                              
                              int kc=Integer.parseInt(temp42)+Integer.parseInt(temp52);
                              System.out.println("sum="+kc);
                              count++;
                              
                              wkinf.write(stf+"-"+temp2+"-"+kc+" ");
                              System.out.print(stf+"-"+temp2+"-"+kc+" ");
                              
                             }
                             }
                          }

                         }
                           wkinf.write(count+"");
                           otwkc.write(count+"");
                           //brk.write(count+"");
                           System.out.print(count);
                           System.out.println();
                           wkinf.newLine();
                           otwkc.newLine();
                           keydist.close();
                           wordc.close();
                           //brk.newLine();
                     }


                   }
                      wkinf.close();
                      otwkc.close();
                      onlywords.close();
                      keydist.close();
                      //brk.close();
                      //reader.close();
                     //return N;
                    // readern.close(
        }
        public void findthreeKeyOcc(File f,int N) throws IOException
        {
            String dmn=f.getName();
            boolean s=new File("D:\\indexfiles\\"+dmn).mkdirs();
                   String word="";
                    try
                    {    
                        wkinf= new BufferedWriter(new FileWriter(new File("D:\\indexfiles\\"+dmn+"\\threekeyinnooffile.txt")));

                    }
                    catch(IOException e)
                    {
                        System.out.println("could not open file");
                       // System.exit(1);
                    }	
                     try
                    {    
                        otkc= new BufferedWriter(new FileWriter(new File("D:\\indexfiles\\"+dmn+"\\onlythreekeycount.txt")));

                    }
                    catch(IOException e)
                    {
                        System.out.println("could not open file");
                       // System.exit(1);
                    }	

                    try
                    {
                        onlywords = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\onlywords.txt")));
                    }
                    catch(Exception e)
                    {
                       System.exit(1);
                    }

                    String filecontent[]=new String[N];
                    String filecontent1[]=new String[N];
                    for(int t=0;t<N;t++)
                    {
                     filecontent1[t]="";
                    }

                    int i=-1;
                    int j=-1;
                    
                     String st=onlywords.readLine();
                     while(st!=null)
                     {
                          if(st.endsWith(".txt"))
                          {
                                  i++;j++;
                          }
                            filecontent[i]+=st+" ";
                            filecontent1[j]+=st+" ";
                            System.out.println(i+" "+st);
                            st=onlywords.readLine();
                     }
                     String fn="";
                     String stn2="",stn3="",str4="";
                      String kyc2="",kyc3="",kyc4="";
                     String stn="",kyc="";
                     String str="";
                     String str2="",str3="";
                     String kyd="";
                     String temp1="",temp2="";
                     int c = 0,c1 = 0,c2;

                     for(int t1=0;t1<N;t1++)
                     {

                      int flag=0;// split the fileContent with space delimiter
                      StringTokenizer tokenizer=new StringTokenizer(filecontent[t1]," ");
                      while(tokenizer.hasMoreTokens()) 
                      {
                          int count=0;

                           try
                           {    
                            keydist= new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\keydist.txt")));

                           }
                           catch(IOException e)
                           {
                              System.out.println("could not open file");
                              // System.exit(1);
                           }	
                           
                           try
                          {
                             wordc = new BufferedReader(new FileReader(new File("D:\\KeywordResult\\"+dmn+"\\wordcount.txt")));
                          }
                           catch(Exception e)
                            {
                              System.out.println("could not open file");
                              System.exit(1);
                            }
                                                
                          if(flag==0)
                          {     
                            stn=tokenizer.nextToken();
                            stn2=tokenizer.nextToken();
                            flag=1;
                          }
                          else
                          {
                            stn=stn2;
                            stn2=stn3;
                          }  

                         stn3=tokenizer.nextToken();
                         if(stn.endsWith(".txt")) 
                         {
                             fn=stn;
                             continue;
                         }    
                         else
                         {
                           stn=stn+","+stn2+","+stn3;
                           wkinf.write(stn+" ");
                           otkc.write(stn+" ");
                           System.out.print(stn+" ");
                           for(int t2=0;t2<N;t2++)
                           {
                            int flag1=0;   
                           StringTokenizer tokenizer1=new StringTokenizer(filecontent1[t2]," ");
                           System.out.println(tokenizer1);       
                           String stf="";
                           step:    while(tokenizer1.hasMoreTokens()) 
                            {
                             // it is a HashSet word
                                //System.out.println("here1");

                          if(flag1==0)
                          {     
                            str=tokenizer1.nextToken();
                            kyc=wordc.readLine();
                            System.out.println(str);
                            str2=tokenizer1.nextToken();
                            kyc2=wordc.readLine();
                            flag1=1;
                            kyd=keydist.readLine();
                            while(!kyd.endsWith(".txt"))
                            {
                            kyd=keydist.readLine();
                            }

                          }
                          else
                          {
                            str=str2;
                            kyc=kyc2;
                            str2=str3;
                            kyc2=kyc3;
                            c=c1;

                          }  

                           str3=tokenizer1.nextToken();
                           kyc3=wordc.readLine();
                           kyd=keydist.readLine();
                            StringTokenizer tokenizer3=new StringTokenizer(kyd," ");
                                temp1=tokenizer3.nextToken();
                                temp2=tokenizer3.nextToken();
                                c1 = new Integer(temp2);
                           if(str.endsWith(".txt"))
                             {    
                                stf=str;

                                continue step;

                             }
                             else
                             {
                              //str2=tokenizer1.nextToken();
                              StringTokenizer tokenizer4=new StringTokenizer(kyc," ");
                              String temp41=tokenizer4.nextToken();
                              String temp42=tokenizer4.nextToken();
                              
                              StringTokenizer tokenizer5=new StringTokenizer(kyc2," ");
                              String temp51=tokenizer5.nextToken();
                              String temp52=tokenizer5.nextToken();
                              
                              StringTokenizer tokenizer6=new StringTokenizer(kyc3," ");
                              String temp61=tokenizer6.nextToken();
                              String temp62=tokenizer6.nextToken();
                              
                              int kc=Integer.parseInt(temp42)+Integer.parseInt(temp52)+Integer.parseInt(temp62);
                              c2=c+c1;
                              str4=str+","+str2+","+str3;
                              System.out.println(str4);
                              if(str4.equals(stn))
                             {
                              count++;
                              wkinf.write(stf+"-"+c2+"-"+kc+" ");
                              System.out.print(stf+ "-"+c2);
                             }
                             }
                          }
                           }//end for

                         }
                           wkinf.write(count+"");
                           otkc.write(count+"");
                           //brk.write(count+"");
                           System.out.print(count);
                           System.out.println();
                           wkinf.newLine();
                           otkc.newLine();
                           keydist.close();
                           wordc.close();
                           //brk.newLine();
                     }//else


                   }
                      wkinf.close();
                      otkc.close();
                      onlywords.close();
                      keydist.close();
                      wordc.close();
                      //brk.close();
                      //reader.close();
                     //return N;
                    // readern.close(
        }
    }
