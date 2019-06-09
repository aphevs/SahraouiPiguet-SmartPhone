package SmartPhone;

import java.awt.Color;

import javax.swing.*;


/**
 * <b>LeftPanel is the class for the left side of the Smartphone and the InfoPanel.</b>
 * <p>
 * LeftPanel is characterized by this information :
 * <ul>
 * <li>A background panel with a color.</li>
 * </ul>
 * </p>
 *
 * @see InfoPanel, SmartPhone
 * @author Ludovic Sahraoui
 * @version 1.0
 */

public class LeftPanel extends App{

	
	/**
	 * Panel for the left part of the InfoPanel and SmartPhone classes
	 */
	private JPanel leftPanel = new JPanel();
	
	
	/**
	 * LeftPanel constructor
	 */
	public LeftPanel() {

		leftPanel.setBackground(Color.DARK_GRAY);
		add(leftPanel);
		
		
	}
	
}
