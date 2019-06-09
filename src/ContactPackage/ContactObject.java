package ContactPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import GalleryPackage.PictureObject;


/* ContactObject is the class of the contact application
* 
* ContactObject is characterized by these information :
* -The index
* -The information : first and last name, numberPhone and the pictureObject from gallery's class
* 
* 
* @author Alexandre Piguet
* @version 3.0
*/

public class ContactObject
{
	//The information of contact, can be updated
	private String firstname, lastname, numberPhone, pictureFile ;
	private PictureObject pictureObject ;
	private int index;

	
	/* Constructor ContactObbject
	 * 
	 *          
	 * @param index
	 *          the index we use to create our contacts
	 *          
	 * @param contactFile
	 *          the file we use to create our contacts                          
	 */
	
	public ContactObject(int index, File contactFile) throws IOException
	{
		String lineSelected = Files.readAllLines(contactFile.toPath()).get(index);
		String[] infoSelected = lineSelected.split(",");
		this.index = index ;
		firstname = infoSelected[0];
		lastname = infoSelected[1];
		numberPhone = infoSelected[2];
		pictureFile = infoSelected[3];
		pictureObject = new PictureObject(pictureFile);
		
	}
	public String getPictureFile() 
	{
		return pictureFile;
	}

	public PictureObject getPictureObject() 
	{
		return pictureObject;
	}

	public void setPictureObject(PictureObject pictureObject) 
	{
		this.pictureObject = pictureObject;
	}
	
	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) 
	{
		this.numberPhone = numberPhone;
	}		
	
	public String getFirstname() 
	{
		return firstname;
	}

	public void setFirstname(String firstname) 
	{
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) 
	{
		this.lastname = lastname;
	}

	public int getIndex() 
	{
		return index;
	}
	

	//TO STRING FOR THE CONTACTPANEL
	@Override
	public String toString() {
		return firstname + " " + lastname;
	}
}