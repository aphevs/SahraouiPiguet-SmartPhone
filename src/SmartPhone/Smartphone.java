package SmartPhone;

import javax.swing.*;

import CalcuPackage.Calcu;
import ContactPackage.Contact;
import GalleryPackage.Gallery;
import NotePadPackage.NotePad;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;



/**
 * <b>SmartPhone is the main frame that contains the north, south, right and left panels. It contains too the App class with changePanel method</b>
 * <p>
 * Smartphone is characterized by these information :
 * <ul>
 * <li>The app that contains appLayout for change panels</li>
 * <li>The south part with buttons home and exit</li>
 * <li>The gray right part of the smartphone</li>
 * <li>The gray left part of the smartphone</li>
 * <li>The north part with hour and battery </li>
 * </ul>
 * </p>
 *
 *
 * @see App, HomePanel, RightPanel, LeftPanel, InfoPanel
 * @author Ludovic Sahraoui, Alexandre Piguet
 * @version 4.0
 */

public class Smartphone extends JFrame
{	
	/**
	 * 
	 * The cardlayout with method for change the panels
	 * 
	 */
    private App app;
    
    /**
	 * 
	 * The south part with the button home and exit
	 * 
	 */
    private HomePanel homePanel;
    
    /**
	 * 
	 * The right gray part of the smartphone
	 * 
	 */
    private RightPanel rightPanel; 
    
    /**
	 * 
	 * The left gray part of the smartphone
	 * 
	 */
    private LeftPanel leftPanel;
    
    /**
	 * 
	 * The north part with the hour and battery
	 * 
	 */
    private InfoPanel infoPanel;


    /**
	 *
    * Smartphone constructor
    * 
    * @throws IOException
    * 
    * @see Smartphone#app
    * @see Smartphone#homePanel
    * @see Smartphone#rightPanel
    * @see Smartphone#leftPanel
    * @see Smartphone#infoPanel
    */
    public Smartphone() throws IOException
    {
    	   	 
        app = new App();    
        homePanel = new HomePanel(app);
        infoPanel = new InfoPanel();
        rightPanel = new RightPanel();
        leftPanel = new LeftPanel();      
        
        setSize(480, 800);
        setLocation(400, 50);
        setResizable(false);
        setUndecorated(true);
        

        app.addCard(new Homescreen(app), "home");
        app.addCard(new Gallery(app), "gallery");
        app.addCard(new Contact(app), "contact");
        app.addCard(new NotePad(app), "notepad");
        app.addCard(new Calcu(app), "calcu");
        
        app.showCard("home");
        
        add(infoPanel, BorderLayout.NORTH);
        add(app, BorderLayout.CENTER);
        add(homePanel, BorderLayout.SOUTH);
        add(rightPanel, BorderLayout.EAST);
        add(leftPanel,  BorderLayout.WEST);
        add(app);

        setVisible(true);
                
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(app, BorderLayout.CENTER);
        this.add(homePanel, BorderLayout.SOUTH);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(leftPanel, BorderLayout.WEST);
        
        
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) { System.exit(0); }
        });       
    }
   

}