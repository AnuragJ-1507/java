
package dataowner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class EncryptFiles 
{
            static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMONPQRSTUVWXYZ0123456789";
	    static int arr[] = {97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,48,49,50,51,52,53,54,55,56,57};
	    private static Cipher encrypt;
	    private static Cipher decrypt;
            BufferedWriter fid=null;
            BufferedWriter fk=null;
            FileOutputStream keyfos=null;
            FileOutputStream filesecretkeys=null;
 	    int id=0;
	    private static final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };
   public EncryptFiles()
   {
             
   }
   public void getNext(File path) throws Exception
   {
            
		File pathlist[]=path.listFiles();
                for (File pathlist1 : pathlist) {
                    if (pathlist1.isDirectory()) {
                        getNext(pathlist1);
                    } else {
                        EncryptFile(pathlist1);
                    }
                }
   }        
   public void EncryptFile(File fn) throws IOException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException 
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
                System.out.println(dmn+str);
                String stn=str+".txt";
                String str1=dataowner.EncryptFiles.encryptdata(str);
                String str2=dataowner.EncryptFiles.encryptdata(str);
                boolean s=new File("D:\\FileKeys\\"+dataowner.EncryptFiles.encryptdata(dmn)).mkdir();
                boolean s1=new File("D:\\encryptedfiles\\"+dmn).mkdir();
                if(str1.endsWith(str2))
                {
                   System.out.println("stirngs are equal"); 
                }
                String encryptedFile = "D:\\encryptedfiles\\"+dmn+"\\"+dataowner.EncryptFiles.encryptdata(str)+".txt";
                
	        //String encryptedFile="D:/encryptedfiles/"+str+".txt";
                try
                {    
                    
                    keyfos = new FileOutputStream("D:\\FileKeys\\"+dataowner.EncryptFiles.encryptdata(dmn)+"\\"+dataowner.EncryptFiles.encryptdata(str)+".txt");
                    
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }
                
                               
                //System.out.println(encryptedFile.getBytes());
                
	        try {
	 
	           // SecretKey secret_key = KeyGenerator.getInstance("DES").generateKey();
                    KeyGenerator keyGen = KeyGenerator.getInstance("DES");
                    keyGen.init(56);
                    Key secret_key = keyGen.generateKey();
                    System.out.println("sec key:"+secret_key);
                    byte b[]= secret_key.getEncoded();
                    keyfos.write(b);
                    
                    
	            AlgorithmParameterSpec alogrithm_specs = new IvParameterSpec(
	                    initialization_vector);
	 
	            // set encryption mode ...
	            encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
	            encrypt.init(Cipher.ENCRYPT_MODE, secret_key, alogrithm_specs);
	 
	            // set decryption mode
	            //decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    //decrypt.init(Cipher.DECRYPT_MODE, secret_key, alogrithm_specs);
	 
	            // encrypt file
	            encrypt(new FileInputStream(fn), new FileOutputStream(
	                    encryptedFile));
	 
	            System.out.println("End of Encryption/Decryption procedure!");
	 
	        } catch (NoSuchAlgorithmException e) {
	        }
	 keyfos.close();
                             
	    }
         public void getFile(File fs) throws Exception
	{
          /*  try
                {    
                    fid = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\fileid.txt")));
                    fk = new BufferedWriter(new FileWriter(new File("D:\\KeywordResult\\encfilekey.txt")));
                    //keyfos = new FileOutputStream("D:\\KeywordResult\\filekey.txt");
                }
                catch(IOException e)
                {
                    System.out.println("could not open file");
                   // System.exit(1);
                }	*/
                int i=1;
	        while(i>0)
		{		
			//fos.write(("<Category name=\""+fs.getName()+"\" path=\""+fs.getPath()+"\">").getBytes());
			getNext(fs);	
                        i--;
		}
                //keyfos.close();
                //fk.close();
                //fid.close();
        }
           private static void encrypt(InputStream input, OutputStream output)
	            throws IOException {
	 
	        output = new CipherOutputStream(output, encrypt);
	        writeBytes(input, output);
	    }
	 
            private static int getAscii(char c){
		/*for(int x=0;x<=chars.length();x++){
			if(chars.charAt(x) == c) {
				return (arr[x]);
			}
		}
		return 0;*/
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
		/*for(int x=0;x<=arr.length;x++) {
			if(arr[x] == i) {
				return (chars.charAt(x));
			}
		}
		return 0;*/
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
