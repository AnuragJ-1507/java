
package dataowner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class UserGui extends JFrame implements ActionListener, ItemListener
{
       
        Label l1,l2;
	JTextField t1;
	JButton bc,fdk,cd,ci,ei,ec;
        EncryptCollection ecs;
        DecryptFiles f3;
        File f;
        Choice ch;
        KeyOcc ko;
        int N;
        String str;
        //ListFiles fl;
        private Component modalToComponent;
	public UserGui()
        {
          super("*******Cloud Software******");
          l1=new Label("Indexing and Advanced Relevance Ranking Score Preserving for Multi-Keyword Search over Encrypted Cloud Data");
          l2=new Label(".........created by Mr. Amol Sawant");
          t1=new JTextField("");
          bc=new JButton("Browse Ecrypted Files");
          fdk=new JButton("Decrypt Files");
          
          ch=new Choice();

          f= new File("");//Font f=new Font();
          //this.setBackground();
          setLayout(null);
          l1.setForeground(Color.BLUE);
          //l1.setFont(f);
          l1.setLocation(20, 20);
          l1.setSize(700, 50);
          t1.setBounds(75,100,300,50);
          bc.setBounds(405, 100, 210,50);
          fdk.setBounds(75, 180,250,50);
          
          t1.addActionListener(this);
          bc.addActionListener(this);
          fdk.addActionListener(this);
          
           add(l1);
           add(t1);
           add(bc);
           add(fdk);
           
         }

   
        
    public void actionPerformed(ActionEvent e)
    {
        
        
        if (e.getActionCommand().equals("Browse Ecrypted Files"))
        {
           	
		        JFileChooser chs=new JFileChooser();
	    		chs.setCurrentDirectory(new java.io.File("."));
                       // chs.setMultiSelectionEnabled(true);
                       
			chs.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			chs.setAcceptAllFileFilterUsed(false);
                        
			if(chs.showOpenDialog(this)==JFileChooser.APPROVE_OPTION)
			{
				f=chs.getSelectedFile();
			}			
			t1.setText(f.getPath());
	  
            
        }
        if (e.getActionCommand().equals("Decrypt Files"))
        {
            f3=new DecryptFiles();
            try {
                f3.getFile(f.getAbsoluteFile());
                //ecs=new EncryptCollection();
                //cpi.findKeywords(f);
            } catch (Exception ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
                       
        }
            
        
    }

   
    public void itemStateChanged(ItemEvent e)
    {
        str=(String)e.getItem();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
       
}
