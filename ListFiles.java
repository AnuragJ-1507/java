package dataowner;
import java.io.*;
import java.util.*;
public class ListFiles
{
	final static int SIZE=16;
	FileOutputStream fos;
	//HexFormatter hf;
	int size;
        
        BufferedWriter writerold = null;
        BufferedWriter writernew = null;
        BufferedWriter writerold1 = null;
        BufferedWriter writernew1 = null;
        BufferedWriter writerold3 = null,writeresult=null;
        BufferedWriter writernew3 = null;
        BufferedWriter writerold2 = null;
        BufferedWriter writernew2 = null;
        BufferedWriter onlywords=null;
        BufferedWriter fileid = null;
        BufferedWriter keywordid = null;
        //BufferedReader temp2 = null;
        BufferedWriter kdist=null;
        BufferedWriter listpathfile=null;
        java.util.HashSet set;
        BufferedWriter listfile=null;
        BufferedWriter wordc=null;
        BufferedWriter relscore=null;
        private String s="";
	int nooffile[],a=0;
        int noofkeywordsold[],b=0;
        int noofkeywordsnew[];
        int keycountold=0,keycountnew=0;
        public ListFiles()
        {
            
            
        }
        public boolean isStopWord(String str)
	{
		return set.contains(str);	
	}
	public void getNext(File path) throws Exception
	{
            
		File pathlistd[]=path.listFiles();
                 boolean s1=new File("D:\\KeywordGraphResult").mkdir();
                try
                {
                writeresult = new BufferedWriter(new FileWriter(new File("D:\\KeywordGraphResult\\result.txt")));
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                    System.out.println("here2");
                   // System.exit(1);
                }
                String line="";
		for(int j=0;j<pathlistd.length;j++)
		{
                    String dmn=pathlistd[j].getName();
                    boolean s=new File("D:\\KeywordResult\\"+dmn).mkdirs();
                    keycountold=0;
                    keycountnew=0;
                    nooffile=new int[20];
                    a=0;
                    b=0;
                    noofkeywordsold=new int[10000];
                    noofkeywordsnew=new int[1000];
                    System.out.println("here1"+dmn);
                        try
                     {    
                    
                    writerold = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\keywordsold.txt")));
                    writernew = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\keywordsnew.txt")));
                    writerold1 = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\oldresult.txt")));
                    writernew1 = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\newresult.txt")));
                    writerold2 = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\oldresultfile.txt")));
                    writernew2 = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\newresultfile.txt")));
                    //writerold3 = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\oldresultlength.txt")));
                   // writernew3 = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\newresultlength.txt")));
                    wordc = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\wordcount.txt")));
                    listpathfile = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\filepathnames.txt")));
                    relscore = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\relevancescore.txt")));
                    listfile = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\filenames.txt")));
                    fileid = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\fileid.txt")));
                    keywordid = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\keywordid.txt")));
                    listfile = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\filenames.txt")));
                    onlywords = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\onlywords.txt")));
                    kdist = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\"+dmn+"\\keydist.txt")));

                    
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                    System.out.println("here2");
                   // System.exit(1);
                }	    
                        
                        
			if(pathlistd[j].isDirectory())
			{
                            File pathlist[]=pathlistd[j].listFiles();
                            for(int i=0;i<pathlist.length;i++)
		            {
                                System.out.println("here3");
                               System.out.println(pathlist[i]); //fos.write((""+pathlist[i].length()).getBytes());
			       listpathfile.write(pathlist[i].toString());
                               System.out.println("here4");
                               listpathfile.newLine();
                               a++;
                               nooffile[a]=a;
                               writerold1.write(a+"");
                               writerold1.write(" ");
                               writernew1.write(a+"");
                               writernew1.write(" ");
                               writerold2.write(pathlist[i].getName()+" ");
                               
                               writernew2.write(pathlist[i].getName()+" ");
                               
                               writerold.write(pathlist[i].toString());
                               writerold.newLine();
                               wordc.write(pathlist[i].getName());
                               onlywords.write(pathlist[i].getName());
                               onlywords.newLine();
                               wordc.newLine();
                               writernew.write(pathlist[i].toString());
                               writernew.newLine();
                               kdist.write(pathlist[i].getName());
                               kdist.newLine();
                               
                              
                              line=readFile(pathlist[i]);
                               
                              writeresult.write(line);
                              writeresult.newLine();
                               String str=pathlist[i].getName();                               
                               System.out.println(str);
                               listfile.write(str);
                               listfile.newLine();
                               
                               //fos.write(" ".getBytes());
                               //System.out.println(nooffile[a]+"  "+a);                                
			}
                        
		}
                        System.out.println("i m here");
		writerold.close();
                writernew.close();
                writerold1.close();
                writernew1.close();
                writerold2.close();
                writernew2.close();
                listpathfile.close();
                relscore.close();
                listfile.close();
                fileid.close();
                keywordid.close();
                wordc.close();
                onlywords.close();
                kdist.close();
                
	}
                
         writeresult.close();
        }
	public String readFile(File f) throws IOException
	{
            
                     
            
	       HashSet<String> hSet=new HashSet<String>();
               BufferedReader reader = null;
               set= new java.util.HashSet();
               String fileContent="";
               String fileContent1="";
               int d=0;
                keycountold=0;keycountnew=0;
                try
                {
                    reader = new BufferedReader(new FileReader(f));
                }
                catch(Exception e)
                {
                    //System.out.println("could not open file");
                    System.exit(1);
                }
                String l = reader.readLine();
                while( l != null)
                {
                    StringTokenizer tokens = new StringTokenizer(l, " '?@#%^&+_-[]!=*()<>{}/,.;:\\\"");
                    while(tokens.hasMoreTokens())
                    {
                        String word = tokens.nextToken();
                        //writerold.write(f.getPath());
                        noofkeywordsold[a]=++keycountold;
                        writerold.write(word);
                        writerold.newLine();
                        //System.out.println("hello");
                        System.out.println(word);
                        System.out.println(nooffile[a]+" "+noofkeywordsold[a]+"-->"+keycountold);
                        
                        String st="";
                        StopStem stm=new StopStem("D:\\stopwords.txt");
                        st=stm.start(word);
                        //writernew.write(f.getPath());
                        if(st.endsWith(""))
                            d++;
                        if(!st.equals(""))
                        {
                            hSet.add(st);
                            fileContent1+=st+" "+d+" ";
                            fileContent+=st+" ";
                            noofkeywordsnew[a]=++keycountnew;
                            writernew.write(st+" "+d);
                            writernew.newLine();
                            System.out.println(st);
                            System.out.println(noofkeywordsnew[a]+"-->"+keycountnew);
                            d=0;
                        }
                    }
                    //read next line
                    l = reader.readLine();
                }
                
                 StringTokenizer tokenizer1=new StringTokenizer(fileContent1," ");
                 while(tokenizer1.hasMoreTokens())
                 {
                   // it is a HashSet word
                  String str=tokenizer1.nextToken();
                  int dt=Integer.parseInt(tokenizer1.nextToken());
                  if (isStopWord(str)) 
                  {
                      continue;
                  } 
                  System.out.println(str);
                  int count=0;

                  // split the fileContent with space delimiter
                  StringTokenizer tokenizer=new StringTokenizer(fileContent," ");
                  while(tokenizer.hasMoreTokens())
                  {
                    if(str.equals(tokenizer.nextToken()))
                    {count++;
                    set.add(str);
                    
                    }
                  }
                   System.out.println(str+" word is repeated "+count+" time in given file");
                   wordc.write(str+" ");
                   kdist.write(str+" ");
                   kdist.write(dt+"");
                   kdist.newLine();
                  // kdist.write(str);
                   onlywords.write(str);
                   wordc.write(count+"");
                   wordc.newLine();
                   onlywords.newLine();
                   
                } 
               writerold1.write(keycountold+"");
               writerold1.newLine();
               writernew1.write(keycountnew+"");
               writernew1.newLine();
               writerold2.write(keycountold+"");
               writerold2.newLine();
               writernew2.write(keycountnew+"");
               writernew2.newLine();
               set.clear();
               reader.close();
               //wordc.close();
               return (keycountold+" "+keycountnew);
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
}
