package SmartPhone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 * <b>Smartphone is the frame class that contains all the elements</b>
 * <p>
 * Smartphone is characterized by these information :
 * <ul>
 * <li>The container that contains button panel and gray side panel</li>
 * <li>The panel with buttons</li>
 * <li>the panel for the south side of the smartphone</li>
 * <li>The buttons home and exit</li>
 * </ul>
 * </p>
 * 
 * @see App
 * @author Ludovic Sahraoui, Alexandre Piguet
 * @version 3.0
 */
public class HomePanel extends App{

	/**
	 * 
	 * The panel that contains homePanel et southPanel
	 * 
	 */
	private JPanel container = new JPanel();
	/**
	 * 
	 * The panel where are home and exit buttons
	 * 
	 */
	private JPanel homePanel = new JPanel();
	/**
	 * 
	 * The panel that is the south part of the smartphone
	 * 
	 */
	private JPanel southPanel = new JPanel();
	/**
	 * 
	 * The button for return on the homescreen of the smartphone
	 * 
	 */
	private JButton home;
	/**
	 * The button for stop the smartphone program
	 */
	private JButton exit;
	
	/**
	 * The loader for read the images of the project
	 */
//	public ClassLoader c1 = this.getClass().getClassLoader();
	
	/**
	 * The image of the home button
	 */
	Icon imgHome = new ImageIcon("/Users/alex/eclipse-workspace/Z_SmartPhone2/Images/home.png");
	/**
	 * The image of the exit button
	 */
	Icon imgExit = new ImageIcon("/Users/alex/eclipse-workspace/Z_SmartPhone2/Images/off.png");
	
	/**
	 * 
	 * HomePanel constructor
	 * 
	 * 
	 * @param app
	 * 	Link with cardlayout class
	 * 
	 * 
	 * @see HomePanel#container
	 * @see HomePanel#homePanel
	 * @see HomePanel#southPanel
	 * @see HomePanel#home
	 * @see HomePanel#exit
	 * @see HomePanel#imgHome
	 * @see HomePanel#imgExit
	 *  
	 */
	public HomePanel(App app) 
	{		
		
		setLayout(new BorderLayout());
		
		container.setLayout(new BorderLayout());
		
		//Background pictures for buttons home and off
		home = new JButton(imgHome);
		home.setBorderPainted(false);
		home.setContentAreaFilled(false);
		
		exit = new JButton(imgExit);
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		
		//Generation of panel with the 2 buttons
		homePanel.add(home);
		homePanel.add(exit);		
        homePanel.setBackground(Color.DARK_GRAY);
		
        //set southPanel
        southPanel.setBackground(Color.DARK_GRAY);
        
        
        container.add(homePanel, BorderLayout.NORTH);
        container.add(southPanel, BorderLayout.SOUTH);
        add(container);
		
        
      //Actions of home and exit button
       home.addActionListener(app.change("home"));
       
       exit.addActionListener(new exitListener());
       
	}
	
	
	//Listener for the button exit
	//action: exit the smartphone
	class exitListener implements ActionListener {
	    public void actionPerformed(ActionEvent arg0){
	      System.exit(0);
	    }
	  }
}
