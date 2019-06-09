package GalleryPackage;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import SmartPhone.App;

/* PictureDetails is the class of the gallery application
* 
* PictureDetails is characterized by these information :
* 
* -The pictureObjects
* 
* @author Alexandre Piguet
* @version 2.0
*/

public class PictureDetails extends App
{
	private static final long serialVersionUID = 7342282236137219027L;

	//The buttons, can't be updated
	private JButton back, delete;
	private JPanel buttonPanel ;
	
	//The label for extending the picture, can be updated, depending on the picture we set
	private JLabel mainLabel;
	
	//The path of a picture, can be updated
	private String picturePath ;
	
	
	/* Constructor Gallery
	 * 
	 *          
	 * @param app
	 *          which we can use the methods from app (cardlayout)
	 *          
	 * @param centerPanel
	 *          where we put the label within the picture we want to extend                              
	 */
	
	public PictureDetails(App app, JPanel centerPanel) 
	{
		//TITLE
		add(new JLabel("PictureDetails"),BorderLayout.PAGE_START);

		//CREATE THE BUTTONS IN SOUTHPANEL
		buttonPanel = new JPanel();
		delete = new JButton("delete");		
		buttonPanel.add(delete);
		back = new JButton("back");		
		back.addActionListener(app.change("gallery"));
		buttonPanel.add(back);
		add(buttonPanel,BorderLayout.PAGE_END);		

		mainLabel = new JLabel();
		add(mainLabel);

		delete.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//DELETE THE PICTURE
				deletePicture(picturePath);
				try {
					//REFRESH THE PANEL GALERY
					removeC("gallery");
					app.addCard(new Gallery(app), "gallery");
					app.showCard("gallery");
				} catch (IOException e1) {
					System.err.println("Error when deleting a picture");
				}			
			}
		});		
	}

	/*
	 * Method for setting the mainLabel with the picture(pictureFile, path) we put on
	 * 
	 */	
	
	public void setImage(String pictureFile) 
	{
		this.picturePath = pictureFile;
		ImageIcon imageIcon = new ImageIcon(pictureFile); // load the image to a imageIcon
		Image img = imageIcon.getImage(); // transform it 
		Image newimg = img.getScaledInstance(mainLabel.getWidth(),mainLabel.getHeight(),Image.SCALE_SMOOTH); // IT TAKES ALL THE LABEL'S SPACE 		
		ImageIcon newImageIcon = new ImageIcon(newimg);  // transform it back
		mainLabel.setIcon(newImageIcon);		
	}

	/*
	 * Method for deleting the pictureFile (path)
	 */
	public void deletePicture(String picturePath)
	{
		File pictureFile = new File(picturePath);	
		pictureFile.delete();
	}
}