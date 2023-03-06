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

public class CreateDomain extends JFrame implements ActionListener
{
    
    File f;
    
    Label l1,l2;
    JTextField t1,t2;
    JButton crd,brf,move;
    String str=""; 
        File[] uploadDir=null;
        File dir=null;
        
    public CreateDomain()
    {
        
          super("*******Cloud Software******");
          l1=new Label("Indexing and Advanced Relevance Ranking Score Preserving for Multi-Keyword Search over Encrypted Cloud Data");
          l2=new Label("Enter the Domain  name");
          t1=new JTextField("");
          crd=new JButton("create domain");
          brf=new JButton("browse files");
          move=new JButton("move files");
                
          setLayout(null);
          
          l1.setLocation(20, 20);
          l1.setSize(700, 50);
          l2.setLocation(75, 100);
          l2.setSize(700, 50);
          t1.setBounds(75,150,300,50);
          crd.setBounds(405, 150, 210,50);
          brf.setBounds(75, 250,250,50);
          move.setBounds(355, 250,260, 50);
         
          t1.addActionListener(this);
          crd.addActionListener(this);
          brf.addActionListener(this);
          move.addActionListener(this);
         
           
           add(l1);
           add(l2);
           add(t1);
           add(crd);
           add(brf);
           add(move);
                                 
}
      
    public void actionPerformed(ActionEvent e)
    {
        
       
        if (e.getActionCommand().equals("create domain"))
        {
          
         str= t1.getText();
         
         boolean s=new File("D:\\Data Collection\\"+str).mkdirs();
                   System.out.println("domain created"); 
        }
        
        if (e.getActionCommand().equals("browse files"))
        {
          JFileChooser fileChooser = new JFileChooser();
          fileChooser.setSelectedFile(new File("D:\\DataOwnerDataCollection"));
          fileChooser.setMultiSelectionEnabled(true);
          fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
          int showOpenDialog = fileChooser.showOpenDialog(null);
          if (showOpenDialog != JFileChooser.APPROVE_OPTION) {
            //return null;
           }
          uploadDir = fileChooser.getSelectedFiles();
          //lastDir = new File(uploadDir[uploadDir.length-1].getParent());
          System.out.println("browse complete"); 
          }
         
        if (e.getActionCommand().equals("move files"))
        {
            
            for(int i=0;i<uploadDir.length;i++)
            {
                String fn="";
                
	     InputStream in = null;
                try {
                    in = new FileInputStream(uploadDir[i]);
                    fn=uploadDir[i].getName();
                    
                    System.out.println(fn);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CreateDomain.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CreateDomain.class.getName()).log(Level.SEVERE, null, ex);
                }
             OutputStream out = null;
                try {
                    out = new FileOutputStream("D:\\Data Collection\\"+str+"\\"+fn);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CreateDomain.class.getName()).log(Level.SEVERE, null, ex);
                }
             byte[] buf = new byte[1024];
             int len;
                try {
                    while ((len = in.read(buf)) > 0)
                    {
                        out.write(buf, 0, len);
                    }  } catch (IOException ex) {
                    Logger.getLogger(CreateDomain.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(CreateDomain.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(CreateDomain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("Files copied");
         }
        
    }
    
}