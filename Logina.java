
package dataowner;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

public class Logina implements ActionListener, ItemListener {

	JLabel l1,l2,l3;
	  JComboBox t1;
	   JButton si;
	   JPasswordField t2;
	   JFrame f1= new JFrame("Data Owner Login");
	   JDesktopPane jdp=new JDesktopPane();
	  ImageIcon img;
	public Logina() {
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();	
		f1.setSize(380,200);
		f1.setLocation(screenSize.width/3,screenSize.height/3);
		f1.setLayout(null);
	        f1.setResizable(false);
		
		l1=new JLabel("User Name :");
		l2=new JLabel("Password :");
		t1= new JComboBox();
		t1.addItem("Administrator");
		t1.addItem("Other User");
		t1.addItemListener(this);
		t2= new JPasswordField();
		si=new JButton("Login");
		si.addActionListener(this);
			f1.add(l1);
			f1.add(t1);
			f1.add(l2);
			f1.add(t2);
		    f1.add(si);
		    img=new ImageIcon("lock1.gif");
		    l3=new JLabel("",img,JLabel.RIGHT);
		l1.setBounds(20,25,82,25);
		t1.setBounds(120,25,145,25);
		l2.setBounds(20,80,82,25);
		t2.setBounds(120,80,145,25);
		si.setBounds(140,125,80,35);
		l3.setBounds(278,20,99,99);
                f1.add(l3);
		f1.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String s=arg0.getActionCommand();
		   if(t1.getSelectedItem().equals("Administrator")) 					 // cheaks User Name
		   		{	
			   		char pa[]={'a','d','s'};
			   		
		   			char p[]=t2.getPassword();
		   			int count=0;
		   		        for(int i=0;i<p.length;i++)					// it Cheaks Password
		   		        {
		   			  count++;
		   			  if(p[i]!=pa[i])
		   			  	{ JDesktopPane Jdp=new JDesktopPane();
		   				JOptionPane.showMessageDialog(Jdp, "InCorrect Password !!!","Error !!!",JOptionPane.ERROR_MESSAGE);
		   				//  System.exit(0);
		   				  break;
		   			  	}
		   			    if(count==3)
		   			    {
		   				  if(s.equals("Login"))					// it  cheak Action
		   				    {	
		   					  MainWindow mw=new MainWindow();
                                                          mw.setLocation(300, 100);
                                                          mw.setSize(700, 500);
                                                          mw.setVisible(true);
                                                          mw.setResizable(true);
                                                          
                                                          f1.setVisible(false);
	
		   				    }
		   			  }
		   			}
		   		}  
                                if(t1.getSelectedItem().equals("Other User")) 	//it is for user side action				 // it cheaks User Name
		   		{	
                                 char pa[]={'a','m','o','l'};
			   		
		   			char p[]=t2.getPassword();
		   			int count=0;
		   		        for(int i=0;i<p.length;i++)					// it Cheaks Password
		   		        {
		   			  count++;
		   			  if(p[i]!=pa[i])
		   			  	{ JDesktopPane Jdp=new JDesktopPane();
		   				JOptionPane.showMessageDialog(Jdp, "InCorrect Password !!!","Error !!!",JOptionPane.ERROR_MESSAGE);
		   				//  System.exit(0);
		   				  break;
		   			  	}
		   			    if(count==4)
		   			    {  
                                               if(s.equals("Login"))	//open new window for user // it cheak Action
                                                {	
                                                         UserGui mw=new UserGui();
                                                         mw.setLocation(300, 100);
                                                          mw.setSize(700, 500);
                                                          mw.setVisible(true);
                                                          mw.setResizable(true);
                                                          
                                                          f1.setVisible(false);
                                                        
                                                          

                                                 }
                                            }
                                        }
		
                                }
        }
	
        
	public void itemStateChanged(ItemEvent arg0) {
		if(arg0.getStateChange()==ItemEvent.SELECTED)
	    {
			  if(t1.getSelectedItem().equals("Other User")) 
			  {
				  t2.setEditable(true);
			  }
			  if(t1.getSelectedItem().equals("Administrator"))
			  {
				  t2.setEditable(true);
			  }
	    }
	}

}
