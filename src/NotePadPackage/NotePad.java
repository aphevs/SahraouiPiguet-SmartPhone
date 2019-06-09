package NotePadPackage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import SmartPhone.App;

/* NotePad is the class of the NotePad application
* 
* NotePad is characterized by these information :
* 
* -The file notePad.txt
* -A textArea
* 
* @author Alexandre Piguet
* @version 2.0
*/

public class NotePad extends App
{
	private static final long serialVersionUID = -8654334558487694759L;
	
	//The buttons, can't be updated
	private JButton save ;
	private JPanel buttonPanel ;
	
	//The textArea, can be updated
	private JTextArea textArea ;
	
	//The file, can be updated
	private File notePadFile = new File("/Users/alex/eclipse-workspace/Z_SmartPhone2/NotePad.txt");

	
	/* Constructor NotePad
	 *          
	 * @param app
	 *          which we can use the methods from app (cardlayout)                               
	 */
	
	public NotePad(App app) throws IOException 
	{
		//TITLE
		add(new JLabel("Note Pad"),BorderLayout.PAGE_START);		

		//The buttons save, can't be updated		
		save = new JButton("save");
		add(save,BorderLayout.PAGE_END);
		
		//The textArea, can be updated
		textArea = new JTextArea(27,25);
		textArea.setLineWrap(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		//LOAD THE CONTENT FROM FILE TO THE TEXTAREA				
		BufferedReader br = new BufferedReader(new FileReader(notePadFile));
		String str;
		while ((str = br.readLine()) != null) 
		{
			textArea.append(str);
		}
		br.close();
		
		
		save.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try{	
					FileWriter fw =  new FileWriter(notePadFile);
					BufferedWriter bw = new BufferedWriter(fw);	
					fw.write(textArea.getText());
					fw.close();
					bw.close();	
				}
				catch (IOException e1) {
					System.err.println("Error when overwritting in notePadFile");
				}
			}
		});		

		add(scrollPane);
	}
}
