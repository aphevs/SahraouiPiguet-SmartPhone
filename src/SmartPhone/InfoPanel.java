package SmartPhone;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

/**
 * 
 * <b>InfoPanel is the north part of the smartphone, with hour and battery</b>
 * <p>
 * InfoPanel is characterized by these information :
 * <ul>
 * <li>The panels that contain information</li>
 * <li>The hour of the smartphone</li>
 * <li>The battery of the smartphone</li>
 * <li>The south side of the smartphone</li>
 * </ul>
 * </p>
 *
 * 
 * @see App
 * @author Ludovic Sahraoui
 * @version 5.0
 */
public class InfoPanel extends App {

	/**
	 * Contains the north and gray side of the smartphone
	 */
	private JPanel container = new JPanel();
	/**
	 * Contains the infoPanel and two gray sides
	 */
	private JPanel container2 = new JPanel();
	/**
	 * Contains the hour, battery and wifi(decorative)
	 */
	private JPanel infoPanel = new JPanel();
	/**
	 * The loader for read the images of the project
	 */
	private ClassLoader c1 = this.getClass().getClassLoader();
	/**
	 * The image of the wifi
	 */ 
	Icon wifiImg = new ImageIcon("/Users/alex/eclipse-workspace/Z_SmartPhone2/Images/wifi.png");
	/**
	 * The variable for the batterystatus
	 * 
	 * @see Kernel32
	 */
	
	
	
	
	/**
	 * 
	 * InfoPanel constructor
	 * 
	 * 
	 * @throws IOException
	 * 
	 * @see InfoPanel#container
	 * @see InfoPanel#container2
	 * @see InfoPanel#infoPanel
	 * 
	 * 
	 */
	public InfoPanel() throws IOException {

		
		
//		Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
		
		// set container
		this.setLayout(new BorderLayout());

		container.setLayout(new BorderLayout());
		container2.setLayout(new BorderLayout());

		// set infoPanel
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setBackground(Color.GRAY);
		
		
		
		//set hour
		TimeLabel hour = new TimeLabel();
		hour.setLayout(new BorderLayout());
		hour.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(hour, BorderLayout.CENTER);
		
		//set batteryLife
		
		
		
		//set wifi Label (decorative)
		JLabel wifi = new JLabel(wifiImg);
		infoPanel.add(wifi, BorderLayout.WEST);
		
		//add infoPanel, right and left gray panels in the container2
		container2.add(new RightPanel(), BorderLayout.EAST);
		container2.add(infoPanel, BorderLayout.CENTER);
		container2.add(new LeftPanel(), BorderLayout.WEST);

		
		//add the container2 and a NorthPanel in the container
		container.add(new NorthPanel(), BorderLayout.NORTH);
		container.add(container2, BorderLayout.SOUTH);
		
		//add and show the container
		add(container);

	}

	/**
	 * 
	 * <b>InfoPanel is the hour label for the infoPanel</b>
	 * <p>
	 * TimeLabel is characterized by these information :
	 * <ul>
	 * <li>A timer for update of the hour</li>
	 * <li>The hour of the smartphone</li>
	 * </ul>
	 * </p>
	 * 
	 * @author Ludovic Sahraoui
	 * @version 1.0
	 *
	 */
	class TimeLabel extends JLabel implements ActionListener {

		/**
		 * 
		 * TimeLabel constructor
		 * 
		 */
		public TimeLabel() {
			Timer t = new Timer(1000, this);
			t.start();
		}

		public void actionPerformed(ActionEvent ae) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			setText(sdf.format(cal.getTime()));
		}
	}

	}