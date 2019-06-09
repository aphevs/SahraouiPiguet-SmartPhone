package ContactPackage;

import javax.swing.*;

import SmartPhone.App;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Files;


/* 
 * Contact is the class of the contact application
 * 
 * Contact is characterized by these information :
 * -The Object create from the file ContactFile
 * -The listModel of contactObjects
 * -The JList of the listModel
 * 
 * 
 * @see Contact,Homescreen,ContactDetails
 * @author Alexandre Piguet
 * @version 5.0
 */

public class Contact extends App
{	
	private static final long serialVersionUID = -6966576059254990415L;

	//The buttons of contact, can't be updated
	private JButton delete, add;
	private JPanel northPanel;

	//The listModel of contact, can be updated
	private DefaultListModel<ContactObject> listModel ;

	//The paths for the contact application
	private File contactFile = new File("/Users/alex/eclipse-workspace/Z_SmartPhone2/Contacts.txt");
	private File tempFile = new File("/Users/alex/eclipse-workspace/Z_SmartPhone2/Temp.txt");



	/* Constructor Contact
	 * 
	 * @param app
	 *          which we can use the methods from app (cardlayout)
	 */

	public Contact(App app) throws IOException
	{
		setName("contact");

		//TITLE	WITH BUTTONS
		northPanel = new JPanel();
		northPanel.add(new JLabel("Contacts list"));
		delete = new JButton("-");
		northPanel.add(delete);
		add = new JButton("+");	
		northPanel.add(add);
		add(northPanel,BorderLayout.PAGE_START);


		//CREATE A LISTMODEL WITH CONTACTOBJETCS FROM FILE, THEN PUT IT IN JLIST AND THEN IN A SCROLLPANE 
		listModel = new DefaultListModel<>();
		for(int i=0; i<getNumberLines() ;i++)
		{
			listModel.addElement(new ContactObject(i,contactFile));
		}	
		JList<ContactObject> list = new JList<ContactObject>(listModel);	
		JScrollPane listScrollPane = new JScrollPane(list);


		//OPEN CONTACTDETAILS PANEL
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				app.removeC("contactDetails");
				if(evt.getClickCount() == 2) {

					//SELECT THE CONTACT(index) WHICH WE WANT THE DETAILS
					int index = list.locationToIndex(evt.getPoint());					

					try {
						app.addCard(new ContactDetails(app,listModel,listModel.get(index), contactFile,tempFile), "contactDetails");
						app.showCard("contactDetails");
					} catch (IOException e) {
						System.err.println("Error when opening contactDetails");
					}				
				}
			}      			
		});

		//ADD A NEW CONTACT
		add.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {

				try {					
					//ADD IN THE LAST LINE OF THE FILE.TXT
					int index = getNumberLines(); 
					BufferedWriter writer = new BufferedWriter(new FileWriter(contactFile,true));					
					String newContact = ("FirstName"+","+"LastName"+","+"PhoneNumber"+",null");

					//TEST IF IT IS FIRST LINE OF FILE
					if(index !=0) 
					{
						//IF NOT, ADD A NEW LINE
						writer.newLine();
					}
					writer.write(newContact);				
					writer.close();

					//ADD AT THE END OF THE LISTMODEL						
					listModel.addElement(new ContactObject(index,contactFile));
					revalidate();

					//THEN SHOW THE CARD CONTACTDETAILS
					app.addCard(new ContactDetails(app,listModel,listModel.get(index), contactFile,tempFile), "contactDetails");
					app.showCard("contactDetails");

				} catch (IOException e1) {
					System.out.println("Error when adding a contact !");
				}
			}
		});

		//DELETE THE CONTACT
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {	
					//DELETE IN FILE.TXT
					removeLine(Files.readAllLines(contactFile.toPath()).get(list.getSelectedIndex()));

					//DELETE IN LISTMODEL
					listModel.removeElementAt(list.getSelectedIndex());
					revalidate();

				} catch (IOException e1) {
					System.out.println("Error when deleting a contact !");
				}
			}
		});

		

		//ADD THE SCROLLPANE TO THE PANEL
		add(listScrollPane, BorderLayout.CENTER);
	}

	/*
	 * Method for removing the line we select from the File contactFile
	 * 
	 * We create a temporary file, write in then overwrite contactFile with it
	 */

	private void removeLine(String lineToRemove) throws IOException 
	{
		BufferedReader br = new BufferedReader(new FileReader(contactFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

		String currentLine;
		int i = 0;
		while((currentLine = br.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if(!trimmedLine.contains(lineToRemove)) {
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

	/*
	 * Method for getting the number of lines from the File contactFile
	 */

	public int getNumberLines() throws IOException 
	{
		LineNumberReader reader  = new LineNumberReader(new FileReader(contactFile));						
		while ((reader.readLine()) != null) {}
		int nbLines = reader.getLineNumber();
		reader.close();
		return nbLines;		
	}
}