
package dataowner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class MainWindow extends JFrame implements ActionListener, ItemListener
{
       
        Label l1,l2;
	JTextField t1;
	JButton bc,fdk,cd,ci,ei,ec,kre,krd,showscore,df,sc;
        CreateIndex cpi;
        CreateDomain cdm;
        ScoreDistribution scd;
        EncryptIndex eic;
        EncryptCollection ecs;
        DecryptFiles f4;
        EncryptFiles f3;
        File f;
        Choice ch;
        KeyOcc ko;
        int N;
        String str;
        ListFiles fl;
        //private Component modalToComponent;
	public MainWindow()
        {
          super("*******Cloud Software******");
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          l1=new Label("Indexing and Advanced Relevance Ranking Score Preserving for Multi-Keyword Search over Encrypted Cloud Data");
          l2=new Label(".........created by Mr. Amol Sawant");
          t1=new JTextField("");
          bc=new JButton("Browse Collection");
          fdk=new JButton("Find Distinct Keywords");
          cd=new JButton("Create Domain");
          ci=new JButton("Create Index");
          ei=new JButton("Encrypt Index");
          ec=new JButton("Encrypt Collection");
          kre=new JButton("Show Keyword Result");
          //krd=new JButton("Decrypt Collection");
          showscore=new JButton("Show Score Result");
          sc=new JButton("Score Distribution Result");
          df=new JButton("Decrypt Files");
          ch=new Choice();

          f= new File("");
         
          setLayout(null);
          l1.setForeground(Color.RED);
          this.setBackground(Color.BLUE);
          //l1.setFont(f);
          l1.setLocation(20, 20);
          l1.setSize(700, 50);
          t1.setBounds(75,100,300,50);
          bc.setBounds(405, 100, 210,50);
          fdk.setBounds(75, 180,250,50);
          cd.setBounds(355, 180,260, 50);
          ci.setBounds(75, 250, 250, 50);
          ei.setBounds(355, 250, 260, 50);
          ec.setBounds(75, 330, 250, 50);
          kre.setBounds(355, 330, 260, 50);
          df.setBounds(75, 400, 250, 50);
          sc.setBounds(355, 400, 260, 50);
         
          t1.addActionListener(this);
          bc.addActionListener(this);
          fdk.addActionListener(this);
          cd.addActionListener(this);
          ci.addActionListener(this);
          ei.addActionListener(this);
          ec.addActionListener(this);
          kre.addActionListener(this);
          df.addActionListener(this);
          sc.addActionListener(this);
           add(l1);
           add(t1);
           add(bc);
           add(fdk);
           add(cd);
           add(ci);
           add(ei);
           add(ec);
           add(kre);
           add(df);
           add(sc);
           
         }

    public void actionPerformed(ActionEvent e)
    {
        
        
        if (e.getActionCommand().equals("Browse Collection"))
        {
           	
		        JFileChooser chs=new JFileChooser();
	    		chs.setCurrentDirectory(new java.io.File("."));
			chs.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chs.setAcceptAllFileFilterUsed(false);
                        
			if(chs.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
			{
				f=chs.getSelectedFile();
			}			
			t1.setText(f.getPath());
	  
            
        }
        if (e.getActionCommand().equals("Find Distinct Keywords"))
        {
         
          try {
                fl=new ListFiles();
                fl.getFile(f.getAbsoluteFile());
                ko=new KeyOcc();
               ko.getFile(new File("D:\\KeywordResult"));  
                             
                
                System.out.println("i am here");
              } catch (Exception ex) {
                System.out.println(ex);
               
            }
        }
        if (e.getActionCommand().equals("Create Domain"))
        {
          System.out.println("U r in domain");
          cdm=new CreateDomain();
    
          cdm.setLocation(300, 100);
          cdm.setSize(700, 500);
          cdm.setVisible(true);
          cdm.setResizable(true);
            System.out.println("U e end of  domain");
        }
        if (e.getActionCommand().equals("Create Index"))
        {
           
            System.out.println("U r in index");
            try { 
                cpi=new CreateIndex();
            } catch (Exception ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("here0");
             try
            {
            cpi.getFile(new File("D:\\KeywordResult")); 
            System.out.println("here1");

           
            }
            catch (Exception ex) {
                
                System.out.println("this ex "+ex);
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getActionCommand().equals("Encrypt Index"))
        {
          eic=new EncryptIndex(); 
            try {
                eic.getFile(new File("D:\\scoreindexfiles"));
            } catch (Exception ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
        if (e.getActionCommand().equals("Decrypt Files"))
        {
              UserGui mw=new UserGui();
              mw.setLocation(300, 100);
              mw.setSize(700, 500);
              mw.setVisible(true);
              mw.setResizable(true);
              //f1.setVisible(false);
                       
        }
        if (e.getActionCommand().equals("Show Keyword Result"))
        {
            
           SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
                            try {
                                new XYLineChartExample().setVisible(true);
                            } catch (IOException ex) {
                                Logger.getLogger(XYLineChartExample.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});  
            
           System.out.println("result complete");
        }
        if (e.getActionCommand().equals("Encrypt Collection"))
        {
                 f3=new EncryptFiles();
            try 
            {
                f3.getFile(f.getAbsoluteFile());
                
            } catch (Exception ex) 
            {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }        
        } //end encrypt colection
        if (e.getActionCommand().equals("Score Distribution Result"))
        {
          System.out.println("U r in score distribution");
          scd=new ScoreDistribution();
          scd.setLocation(300, 100);
          scd.setSize(700, 500);
          scd.setVisible(true);
          scd.setResizable(true);
          System.out.println("U e end of  score distribution");
        }      
    }   
    public void itemStateChanged(ItemEvent e)
    {
        str=(String)e.getItem();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
