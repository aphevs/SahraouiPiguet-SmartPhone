package SmartPhone;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;


/**
 * <b>SmartPhone is the frame class, that contains all the parts</b>
 * <p>
 * Le SmartPhone is characterized by this information :
 * <ul>
 * <li>The buttons of applications.</li>
 * <li>The wallPaper</li>
 * </ul>
 * </p>
 *
 * 
 * @see App, HomePanel
 * @author Ludovic Sahraoui, Alexandre Piguet
 * @version 4.0
 */
public class Homescreen extends App
{
    /**
     * The button for launch the contact application
     */
    private JButton contact ;
    /**
     * The button for launch the calculator application
     */
    private JButton calcu;
    /**
     * The button for launch the gallery application
     */
    private JButton gallery;
    /**
     * The button for launch notePad application
     */
    private JButton notePad;
    /**
     * The loader for read the images of the project
     */
//    private ClassLoader c1 = this.getClass().getClassLoader();
    /**
     * The image of the contact button
     */
    Icon imgContact = new ImageIcon("/Users/alex/eclipse-workspace/Z_SmartPhone2/Images/contact.png");
    /**
     * The image of the gallery button
     */
    Icon imgGallery = new ImageIcon("/Users/alex/eclipse-workspace/Z_SmartPhone2/Images/galerie.png");
    /**
     * The image of the notepad button
     */
    Icon imgBlocNote = new ImageIcon("/Users/alex/eclipse-workspace/Z_SmartPhone2/Images/blocNote.png");
    /**
     * The image of the calculator button
     */
    Icon imgCalcu = new ImageIcon("/Users/alex/eclipse-workspace/Z_SmartPhone2/Images/Calcu.png");
    /**
     * The wallpaper image
     */
    private Image sourceImage;
    
    /**
     * Homescreen constructor
     * 
     * @param app
     * @throws IOException
     * 
 	 * @see HomePanel#contact
	 * @see HomePanel#calcu
	 * @see HomePanel#gallery
	 * @see HomePanel#imgContact
	 * @see HomePanel#imgGallery
	 * @see HomePanel#imgBlocNote
	 * @see HomePanel#imgCalcu
	 * @see HomePanel#img
     */
    public Homescreen(App app) throws IOException
    {
    	
    	setLayout(new FlowLayout());

    	//set image for the wallpaper

//    	sourceImage = Toolkit.getDefaultToolkit().getImage(getClass().getResource("./Images/WallPaper.jpg"));
    	
    	sourceImage = Toolkit.getDefaultToolkit().getImage("/Users/alex/eclipse-workspace/Z_SmartPhone2/Images/Wallpaper.jpg");
    	
    	//set applications
    	contact = new JButton(imgContact);
        contact.addActionListener(app.change("contact"));
        contact.setBorderPainted(false);
        contact.setContentAreaFilled(false);
     
        gallery = new JButton(imgGallery);
        gallery.addActionListener(app.change("gallery"));
        gallery.setBorderPainted(false);
        gallery.setContentAreaFilled(false);
        
        calcu = new JButton(imgCalcu);
        calcu.addActionListener(app.change("calcu"));
        calcu.setBorderPainted(false);
        calcu.setContentAreaFilled(false);
        
        
        notePad = new JButton(imgBlocNote);
        notePad.addActionListener(app.change("notepad"));
        notePad.setBorderPainted(false);
        notePad.setContentAreaFilled(false);
        
        //add applications
        add(contact);
        add(gallery);
        add(calcu);
        add(notePad);       
    }
    
    /**
     * Set the wallpaper
     *
     * @param g
     *	The function that create our image and set the wallpaper
     */
    public void paintComponent(Graphics g) {
           	
    	g.drawImage(sourceImage, 0, 0, null);
    }    
}