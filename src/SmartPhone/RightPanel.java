package SmartPhone;

import java.awt.Color;

import javax.swing.*;


/**
 * <b>RightPanel est la classe representant la barre grise sur la droite de la JFrame et qui compose la classe InfoPanel.</b>
 * <p>
 * The RightPanel is characterized by ths information :
 * <ul>
 * <li>The background color</li>
 * </ul>
 * </p>
 *
 * 
 * @see InfoPanel, SmarthPhone
 * @author Ludovic Sahraoui
 * @version 1.0
 */

public class RightPanel extends App{

	
	/**
	 * 
	 * The panel for the right part of the InfoPanel and Smartphone classes
	 * 
	 */
	
	private JPanel rightPanel = new JPanel();
	
	/**
	 * RightPanel constructor
	 * 
	 * 
	 */
	public RightPanel() {
		
		
		//Set the background of the panel
		rightPanel.setBackground(Color.DARK_GRAY);
		//Add the panel
		add(rightPanel);
		
		
	}
	
}
