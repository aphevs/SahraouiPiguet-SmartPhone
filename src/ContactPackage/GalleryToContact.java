package ContactPackage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JLabel;
import javax.swing.JPanel;
import GalleryPackage.PictureObject;
import SmartPhone.App;
import net.miginfocom.swing.MigLayout;

/* GalleryToTontact is the class of the contact application
* 
* GalleryToTontact is characterized by these information :
* 
* -The pictureObjects
* 
* @author Alexandre Piguet
* @version 2.0
*/


public class GalleryToContact extends App
{

	private static final long serialVersionUID = -7038176110554067623L;
	
	private JPanel centerpanel;
	
	//The pictureObject, can't be updated in this panel
	private PictureObject pictureObject;
	
	/* Constructor GalleryToTontact
	 * 
	 *          
	 * @param app
	 *          which we can use the methods from app (cardlayout)
	 *          
	 * @param ContactObject
	 *          the object which contains the informations about the contact
	 *          
	 * @param contactFile
	 *          the file we use to create our contacts
	 *            
	 * @param tempFile
	 *         the file we use to overwritting the contactFile (modidfy, add and delete)                       
	 */
	
	public GalleryToContact(App app,ContactObject contactObject,File contactFile,File tempFile) throws IOException 
	{
		//TITLE
		add(new JLabel("Gallery to Contact"),BorderLayout.PAGE_START);

		//PANEL WHERE THE PICTURES WILL DISPLAY
		centerpanel = new JPanel();		
		centerpanel.setLayout(new MigLayout("wrap 4"));

		//CREATE THE BUTTONS/IMAGES USING THE LENGTH OF THE NUMBER OF PICTURES IN THE IMAGES REPERTORY	
		pictureObject = new PictureObject(0);
		for(int i=1; i<pictureObject.getNumberPictures(); i++)
		{
			//CREATE THE OBJECT PICTURE AND ADD IT THE THE PANEL
			PictureObject p = new PictureObject(i);
			centerpanel.add(p);

			p.addActionListener(new ActionListener() {						
				@Override
				public void actionPerformed(ActionEvent e) {
						
					try {	
						//ADD THE PATH OF THE PICTURE TO THE CONTACT IN THE FILE
						BufferedWriter writer;
						writer = new BufferedWriter(new FileWriter(contactFile,true));
						String newContact = (contactObject.getFirstname()+"," + contactObject.getLastname()+"," + contactObject.getNumberPhone()+"," +p.getPathFile() );
						writer.newLine();
						writer.write(newContact);					
						writer.close();
						
						//SET THE CONTACTOBJECT & REMOVE THE OLD CONTACT(LINE) IN THE FILE
						contactObject.setPictureObject(p);					
						removeLine(Files.readAllLines(contactFile.toPath()).get(contactObject.getIndex()), contactFile, tempFile);
						
						//REFRESH THE CONTACTPANEL
						removeC("contact");
						app.addCard(new Contact(app),"contact");
						app.showCard("contact");
						
					} catch (IOException e1) {
						System.err.println("Error when adding a image to a contact");
					}					
				}
			});
		}
		add(centerpanel);
	}
	
	/*
	 * Method for removing the line we select from the File contactFile.
	 * We create a temporary file, write in then overwrite contactFile with it.
	 */
	
	private void removeLine(String lineToRemove, File contactFile, File tempFile) throws IOException {

		BufferedReader br = new BufferedReader(new FileReader(contactFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

		String currentLine;
		int i = 0;
		while((currentLine = br.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if(!trimmedLine.contains(lineToRemove)) 
			{
				if(i == 0){
					bw.write(currentLine);					
				}
				else{
					bw.write("\n" + currentLine);					
				}
				i++;
			}
		}
		bw.close();
		br.close();
		tempFile.renameTo(contactFile);
	}
}
