package GalleryPackage;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/* PictureObject is the class of the gallery application
* 
* PictureObject is characterized by these information :
* 
* -The file Pictures
* -The index
* 
* @author Alexandre Piguet
* @version 2.0
*/

public class PictureObject extends JButton
{	
	private static final long serialVersionUID = 1510807213218729439L;
	
	//The File we use to create our pictureObject, can be updated
	private File repertory = new File("/Users/alex/eclipse-workspace/Z_SmartPhone2/Pictures/");
	private File [] picturesTab ;
	private int index ;
	private ImageIcon imageIcon ;
	
	
	/* Constructor PictureObject        
	 * @param index
	 *          which we can create our pictureObject from the repertory Pictures                             
	 */
	
	public PictureObject(int index) throws IOException 
	{
		picturesTab = repertory.listFiles();
		this.index = index ;
		imageIcon = new ImageIcon(picturesTab[index].getPath() ); // LOAD THE IMAGE TO A IMAGEICON
		setImageIconToPO(imageIcon);	
	}
	
	/* Constructor PictureObject
	 * 
	 *          
	 * @param path
	 *          when we adding a new picture, we create a new constructor with simply a path into it                             
	 */
	
	public PictureObject(String path) 
	{
		imageIcon = new ImageIcon(path); // LOAD THE IMAGE TO A IMAGEICON
		setImageIconToPO(imageIcon);		
	}	
	
	/*
	 * Method for setting the imageIcon to the pictureObject
	 */		
	public void setImageIconToPO(ImageIcon imageIcon)
	{		
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it  
		imageIcon = new ImageIcon(newimg);  // transform it back		
		setIcon(imageIcon); //SET THE IMAGEICON TO THE BUTTON
	}	

	
	/*
	 * Method for getting the number of pictures in our repertory Pictures
	 */	
	public int getNumberPictures()
	{
		int numberPictures=0 ;

		while(numberPictures<picturesTab.length)			
		{
			numberPictures++ ;		
		}
		return numberPictures;		
	}	

	public String getPathFile()
	{
		String path = picturesTab[index].getPath();
		return path ;		
	}	
}