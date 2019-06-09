package SmartPhone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* App is the class of the cardlayout
* 
* App is characterized by these information :
* 
* -The panel cards
* 
* @author Alexandre Piguet
* @version 2.0
*/

public class App extends JPanel
{
	//The cards, can be updated
	private JPanel cards;
	private CardLayout cardLayout;

	/* 
	 * Constructor App
	 */
	public App ()
	{
		setName("default");
		setLayout(new BorderLayout());

		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);

		add(cards, BorderLayout.CENTER);
	}
	
	/*
	 * Method for showing the panel we want
	 */
	public ActionListener change (String card)
	{
		return new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				showCard(card);
			}
		};
	}
	
	
	/*
	 * Method for removing the old panel, in fact for refreshing the panel we want to
	 */
	public void removeC (String s)
	{
		Component[] components = cards.getComponents();

		for(int i = 0; i < components.length; i++) 
		{			
		    if(components[i] != null && components[i].getName().equals(s)) 
		    {
		        cards.remove(components[i]);
		    }
		} 
	}
	
	//Method for showing the card, in the cardLayout
	public void showCard(String s)
	{
		cardLayout.show(cards, s);
	}

	//Method for adding the card, in cards
	public void addCard(JPanel p, String s)
	{
		cards.add(p,s);
	}
}