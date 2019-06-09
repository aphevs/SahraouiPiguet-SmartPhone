package SmartPhone;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;


/**
 * <b>NorthPanel is the class for the north side of the InfoPanel.</b>
 * <p>
 * NorthPanel is characterized by this information :
 * <ul>
 * <li>A background panel with a color.</li>
 * </ul>
 * </p>
 *
 * 
 * @see InfoPanel
 * @author Ludovic Sahraoui
 * @version 1.0
 */
public class NorthPanel extends App {

	
	
	/**
	 * 
	 * Panel for the north part of the InfoPanel class
	 * 
	 */
	private JPanel northPanel = new JPanel();


	
	/**
	 * NorthPanel constructor
	 * 
	 * 
	 */
	public NorthPanel() {

		this.setLayout(new BorderLayout());
		northPanel.setBackground(Color.DARK_GRAY);
		add(northPanel);
	}
	
}
