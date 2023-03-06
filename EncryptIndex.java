
package dataowner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.StringTokenizer;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EncryptIndex 
{
            static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMONPQRSTUVWXYZ0123456789";
	    static int arr[] = {97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,48,49,50,51,52,53,54,55,56,57};
	    private static Cipher encrypt;
	    private static Cipher decrypt;
            BufferedWriter fid=null;
            BufferedWriter brold=null;
            BufferedWriter fk=null;
            FileOutputStream keyfos=null;
            FileOutputStream filesecretkeys=null;
 	    int id=0;
	    private static final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };
    private BufferedWriter br=null;
    private BufferedReader br1=null;
   public EncryptIndex()
   {
             
   }
   public void getNext(File path) throws Exception
   {
            
		File pathlist[]=path.listFiles();
                for (File pathlist1 : pathlist) 
                {
                    if (pathlist1.isDirectory())
                    {
                        getNext(pathlist1);
                    } else 
                    {
                        EncryptIndexFile(pathlist1);
                    }
                }
               
   }        
   public void EncryptIndexFile(File fn) throws IOException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException 
   {
                id++;
                String dmn=fn.getParent();
                
                StringTokenizer tok=new StringTokenizer(dmn,":\\");
                 tok.nextToken();
                 tok.nextToken();
                 dmn=tok.nextToken();
               
                String abc=fn.getName();
                int i=abc.length();
                String str=abc.substring(0, i-4);
                System.out.println(str);
                String stn=str+".txt";
                boolean s=new File("D:\\Indexupload\\"+dmn).mkdirs();
                
                try
                {    
                    br= new BufferedWriter(new FileWriter(new File("D:\\Indexupload\\"+dmn+"\\"+str+".txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file br");
                   // System.exit(1);
                }
                
                try
                {    
                    br1= new BufferedReader(new FileReader(fn));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file br1");
                   // System.exit(1);
                }
	        String line=br1.readLine();
                System.out.println(line);
                String temp="";
               
                while(line!=null)
                {
                 temp=line;  
                 StringTokenizer token = new StringTokenizer(line," ");
                 String kw=token.nextToken();
                 String ekw=dataowner.EncryptFiles.encryptdata(kw);
                 br.write(ekw+" ");
                   if(!dmn.equals("oldindex"))
                {
                 brold.write(ekw+" ");
                }
                 while(token.hasMoreTokens()) 
                  {
                      stn=token.nextToken();
                                       
                      if(stn.endsWith(".txt"))
                      {
                          i=stn.length();
                          String filen=stn.substring(0, i-4);
                          String ekf=dataowner.EncryptFiles.encryptdata(filen)+".txt";
                          br.write(ekf+" ");
                            if(!dmn.equals("oldindex"))
                            {
                          brold.write(ekf+" ");
                            }
                      }
                      else
                      {
                          String ekf=dataowner.EncryptFiles.encryptdata(stn);
                          br.write(ekf+" ");
                            if(!dmn.equals("oldindex"))
                          {
                          brold.write(ekf+" ");
                          }
                      }
                      
                  }
                 line=br1.readLine();
                 br.newLine();
                   if(!dmn.equals("oldindex"))
                {
                 brold.newLine();
                }
               }
               br.close();
               
               br1.close();
             
                
   }
         public void getFile(File fs) throws Exception
	{
            boolean s=new File("D:\\Indexupload\\oldindex").mkdirs();
            try
                {    
                    brold= new BufferedWriter(new FileWriter(new File("D:\\Indexupload\\oldindex\\oldindextime.txt")));
                  
                }
                catch(IOException e)
                {
                    System.out.println("could not open file br");
                   // System.exit(1);
                }
                int i=1;
	        while(i>0)
		{		
			getNext(fs);	
                        i--;
		}
                 brold.close();
                
        }
           private static void encrypt(InputStream input, OutputStream output)
	            throws IOException {
	 
	        output = new CipherOutputStream(output, encrypt);
	        writeBytes(input, output);
	    }
	 
            private static int getAscii(char c){
	    return (int)c;
	}
            
           public  static String encryptdata(String passwd) {
		int i,len,asci_code,temp_val=0;
		char ch;
		String out_str = new String();
		len = passwd.length();
		for(i=0;i<len;i++) {
			ch = passwd.charAt(i);
			asci_code = getAscii(ch);
			if(i < len/2) {
				temp_val = (asci_code * 3) + 10;
			}else {
				temp_val = (asci_code * 4) + 11;
			}
			out_str = out_str + temp_val;
		}
		return(out_str);
	}
   
            
	    private static void decrypt(InputStream input, OutputStream output)
	            throws IOException {
	 
	        input = new CipherInputStream(input, decrypt);
	        writeBytes(input, output);
	    }
            
            
            static char getCharec(int i) {
		return (char)i;
	}
            
            public static String decrypt1( String passwd ){
		int i,len,asci_code,temp_val=0;
		String out_str = new String();
		String char_str = new String();
		len = passwd.length();
		for(i=0;i<len;i+=3)	{
			if((i+3) <= len){
				char_str = passwd.substring(i,i+3);
				asci_code = Integer.parseInt(char_str);
				if(i<(len - 2)/2) {
					temp_val = ((asci_code - 10)/3);
				} else {
					temp_val = ((asci_code - 11)/4);
				}
			}
			out_str = out_str + dataowner.EncryptFiles.getCharec(temp_val);
		}
		return(out_str);
	}

	 
	    private static void writeBytes(InputStream input, OutputStream output)
	            throws IOException {
	        byte[] writeBuffer = new byte[512];
	        int readBytes = 0;
	 
	        while ((readBytes = input.read(writeBuffer)) >= 0) {
	            output.write(writeBuffer, 0, readBytes);
	        }
	 
	        output.close();
	        input.close();
	    }

    
}
