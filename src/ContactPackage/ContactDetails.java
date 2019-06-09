package ContactPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GalleryPackage.PictureObject;
import SmartPhone.App;

/* 
 * ContactDetails is the class of the contact's details application
 * 
 * ContactDetails is characterized by these information :
 * -The Object from contact application
 * -The JTextFields
 * -The PictureObject from gallery's class
 * 
 * 
 * @see Contact,ContactDetails,GalleryToContact
 * @author Alexandre Piguet
 * @version 5.0
 */

public class ContactDetails extends App
{
	private static final long serialVersionUID = 9157931194422648494L;

	//The buttons of contact, can't be updated
	private JButton back, modify, ok ;
	
	//The two panels for informations and the back button
	private JPanel namesPanel = new JPanel(new FlowLayout()) ;
	private JPanel buttonPanel = new JPanel();
	
	//The pictureOJbect linked to the contact, can be updated
	private PictureObject pictureObject ;	
	
	/* Constructor ContactDetails
	 * 
	 * @param app
	 *          which we can use the methods from app (cardlayout)
	 *
	 * @param listModel
	 *          the list we will put int the objects
	 *          
	 * @param ContactObject
	 *          the object which contains the informations about the contact, from the file
	 *          
	 * @param contactFile
	 *          the file we use to create our contacts
	 *          
	 * @param tempFile
	 *          the file we use to overwritting the contactFile (modidfy, add and delete)                           
	 */

	public ContactDetails(App app, DefaultListModel<ContactObject> listModel, ContactObject contactObject, File contactFile,File tempFile) throws IOException 
	{
		setName("contactDetails");

		//TITLE
		add(new JLabel("Contact Details"),BorderLayout.PAGE_START);

		//BUTTONS
		back = new JButton("back");
		modify = new JButton("modify");
		ok = new JButton("ok");

		//TO ADD A PICTURE TO A CONTACT,WE OPEN A NEW PANEL
		pictureObject = contactObject.getPictureObject();
		GalleryToContact galleryToContact = new GalleryToContact(app,contactObject,contactFile,tempFile);
		app.addCard(galleryToContact , "galleryToContact");
		pictureObject.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{			
				app.showCard("galleryToContact");
			}
		});

		//CREATE THE 3 TEXTFIELDS
		JTextField firstNameField = new JTextField(contactObject.getFirstname());
		JTextField lastNameField = new JTextField(contactObject.getLastname());		
		JTextField phoneField = new JTextField(contactObject.getNumberPhone());

		//TEST IF IT'S A NEW CONTACT OR NOT
		if((firstNameField.getText()).equals("FirstName") && (lastNameField.getText()).equals("LastName") && (phoneField.getText()).equals("PhoneNumber"))
		{
			firstNameField.setEnabled(true);
			lastNameField.setEnabled(true);
			phoneField.setEnabled(true);
			modify.setVisible(false);
			back.setVisible(false);
		}
		else 
		{
			firstNameField.setEnabled(false);
			lastNameField.setEnabled(false);
			phoneField.setEnabled(false);
			ok.setEnabled(false);
		}
		namesPanel.add(pictureObject);
		namesPanel.add(firstNameField);
		namesPanel.add(lastNameField);
		namesPanel.add(new JLabel("+41"));
		namesPanel.add(phoneField);

		add(namesPanel,BorderLayout.WEST);


		//MODIFY A CONTACT
		modify.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {

				//THE USER CAN MODIFY THE TEXTFIELDS
				firstNameField.setEnabled(true);
				lastNameField.setEnabled(true);
				phoneField.setEnabled(true);
				ok.setEnabled(true);
			}
		});

		//SAVE THE MODIFICATIONS	
		ok.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {			

				//THE USER CAN'T MODIFY THE JTEXTFIELD
				firstNameField.setEnabled(false);
				lastNameField.setEnabled(false);
				phoneField.setEnabled(false);

				try {

					//ADD THE CONTACT MODIFIED(NEW CONTACT) 
					BufferedWriter writer = new BufferedWriter(new FileWriter(contactFile,true));										
					String newContact = (firstNameField.getText()+"," + lastNameField.getText()+"," + phoneField.getText()+","+contactObject.getPictureFile() );
					writer.newLine();
					writer.write(newContact);					
					writer.close();					

					//SET THE OBJECT WITH THE FIELDS OF THE NEW CONTACT
					contactObject.setFirstname(firstNameField.getText());
					contactObject.setLastname(lastNameField.getText());
					contactObject.setNumberPhone(phoneField.getText());
					listModel.addElement(contactObject);

					//DELETE OLD CONTACT
					listModel.removeElementAt(contactObject.getIndex());						
					revalidate();
					removeLine(Files.readAllLines(contactFile.toPath()).get(contactObject.getIndex()), contactFile, tempFile);

					//REFRESH THE CONTACTPANEL AND THE LIST
					removeC("contact");
					app.addCard(new Contact(app),"contact");
					app.showCard("contact");

				} catch (IOException e1) 
				{
					System.err.println("Error when modifying the contact !");
				}
			}
		});

		buttonPanel.add(modify);
		buttonPanel.add(ok);

		//BACK ACTION
		back.addActionListener(app.change("contact"));
		buttonPanel.add(back);
		add(buttonPanel,BorderLayout.PAGE_END);
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
