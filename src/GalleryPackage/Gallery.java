package GalleryPackage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import SmartPhone.App;
import net.miginfocom.swing.MigLayout;

/* Gallery is the class of the gallery application
* 
* Gallery is characterized by these information :
* 
* -The pictureObjects
* -The pictureDetails
* -The repertories Pictures and PictureToTake
* 
* @author Alexandre Piguet
* @version 3.0
*/

public class Gallery extends App 
{	
	private static final long serialVersionUID = -2651245242584375443L;

	//The button, can't be updated
	private JButton add ;
	private JPanel centerpanel, northPanel ;
	
	//The pictureObject, can be updated in this panel
	private PictureObject pictureObject = new PictureObject(0) ;
	private PictureDetails pictureDetails ;

	
	/* Constructor Gallery
	 * 
	 *          
	 * @param app
	 *          which we can use the methods from app (cardlayout)
	 *                                
	 */
	
	public Gallery(App app) throws IOException 
	{
		//TITLE	WITH BUTTONS
		northPanel = new JPanel();
		northPanel.add(new JLabel("Gallery"));
		add = new JButton("+");
		northPanel.add(add);
		add(northPanel,BorderLayout.PAGE_START);

		//CREATE THE NEW PICTURESPANEL AND SET THE LAYOUT WITH MIGLAYOUT
		pictureDetails = new PictureDetails(app,centerpanel);
		app.addCard(pictureDetails,"pictureDetails");
		centerpanel = new JPanel();		
		centerpanel.setLayout(new MigLayout("wrap 4"));

		//CREATE THE BUTTONS/IMAGES USING THE LENGTH OF THE NUMBER OF PICTURES IN THE IMAGES REPERTORY		
		for(int i=1; i<pictureObject.getNumberPictures(); i++)
		{
			PictureObject p = new PictureObject(i);
			centerpanel.add(p);

			//SHOW THE PICTURE'S DETAIL
			p.addActionListener(showPicture(p.getPathFile(),app, p));
		}

		//ADD A PICTURE FROM REPERTORY "PICTURESTOTAKE"
		add.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {

				//SELECT THE PICTURE WE WANT TO ADD AND CREATE A NEW PICTUREOBJECT
				JFileChooser jfc = new JFileChooser();
				jfc.setCurrentDirectory(new File("/Users/alex/eclipse-workspace/Z_SmartPhone2/PicturesToTake/"));
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
					File selectedFile = jfc.getSelectedFile();
					PictureObject p = new PictureObject(selectedFile.getAbsolutePath());
					centerpanel.add(p);
					revalidate();					

					//MOVE THE IMAGEFILE FROM REPERTORY PICTURETOTAKE TO PICTURES
					File newFile = new File("/Users/alex/eclipse-workspace/Z_SmartPhone2/Pictures/" + selectedFile.getName());					
					selectedFile.renameTo(newFile);

					//SHOW THE PICTURE'S DETAIL
					p.addActionListener(showPicture(newFile.getPath(),app, p));
				}				
			}
		});

		JScrollPane listScrollPane = new JScrollPane(centerpanel);
		add(listScrollPane,BorderLayout.CENTER);
	}

	/*
	 * Method for showing(set) the picture we want to extend, in another panel: PictureDetails
	 */	
	public ActionListener showPicture(String path, App app, PictureObject pictureObject)
	{
		return new ActionListener( ) {
			public void actionPerformed(ActionEvent e) 
			{
				pictureDetails.setImage(path);
				app.showCard("pictureDetails");
			}
		};
	}
}
