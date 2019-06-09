package CalcuPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import SmartPhone.App;

/**
 * Inspired by this: https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/23832-tp-creez-une-calculatrice
 * 
 * <b>Calcu is the class of the calculator application</b>
 * <p>
 * Calcu is caracterised by these information :
 * <ul>
 * <li>Table of buttons and name of buttons</li>
 * <li>The calculator screen</li>
 * <li>The dimensions of buttons</li>
 * <li>The result of calculation</li>
 * <li>The variable that allow to modify screen</li>
 * <li>The operator for the calculation</li>
 * </ul>
 * </p>
 *
 * 
 * @see App, HomeScreen
 * @author Ludovic Sahraoui
 * @version 3.0
 */

public class Calcu extends App {


	/**
	 * 
	 * Table of label of buttons, can't be update
	 * 
	 */
	private String[] tab_string = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/" };

	/**
	 * 
	 * Table of buttons, can't be update. Same length than tab_string
	 * 
	 * 
	 */
	private JButton[] tab_button = new JButton[tab_string.length];
	/**
	 * 
	 * The screen of the calculator, can be update
	 * 
	 * @see	Calcu#calcul()
	 * 
	 */
	private JLabel ecran = new JLabel();
	/**
	 * 
	 * Dimension of the numbers buttons
	 * 
	 */
	private Dimension dimButtons = new Dimension(30, 30);
	/**
	 * 
	 * Dimension of the operations buttons
	 * 
	 */
	private Dimension dimOpe = new Dimension(30, 31);
	/**
	 * 
	 * The result showed on the screen after calculate
	 * 
	 * @see Calcu#calcul()
	 * 
	 */
	private double result;
	/**
	 * 
	 * The variable that allow to write the next number on the screen, or calculate if we don't want the equal now
	 * 
	 */
	private boolean clicOperateur = false;	
	/**
	 * 
	 * The variable that allow to update the numbers on screen
	 * 
	 */
	private boolean update = false;
	/**
	 * 
	 * The operator that will choose the calculation
	 * 
	 * @see Calcu#calcul()
	 * 
	 */
	private String operator = "";

	/**
	 * 
	 * Calcu constructor
	 * 
	 * 
	 * @param app
	 * 		Link with the cardlayout and homescreen class
	 * 
	 * @see Calcu#container
	 * @see Calcu#tab_string
	 * @see	Calcu#tab_button
	 * @see Calcu#ecran
	 * @see Calcu#dimButtons
	 * @see Calcu#dimOpe
	 * 
	 * 
	 * 
	 */
	public Calcu(App app) {

		// Definition of fonts

		Font screenFont = new Font("Arial", Font.BOLD, 20);
		ecran = new JLabel("0");
		ecran.setFont(screenFont);

		Font opeFont = new Font ("Arial", Font.BOLD, 15);
		
		Font numberFont = new Font ("Arial", Font.BOLD, 30);

		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setPreferredSize(new Dimension(220, 20));
		
		//Create the panels
		JPanel opePan = new JPanel();
		opePan.setPreferredSize(new Dimension(55, 225));
		JPanel numberPan = new JPanel();
		numberPan.setPreferredSize(new Dimension(165, 225));
		JPanel panEcran = new JPanel();
		panEcran.setPreferredSize(new Dimension(220, 30));

		// Generation of the buttons
		for (int i = 0; i < tab_string.length; i++) {
			tab_button[i] = new JButton(tab_string[i]);
			tab_button[i].setPreferredSize(dimButtons);
			
			tab_button[i].setContentAreaFilled(false);
			
			//Add font and actionListeners on buttons
			switch (i) {

			case 11:
				tab_button[i].addActionListener(new EgalListener());
				numberPan.add(tab_button[i]);
				tab_button[i].setFont(opeFont);
				tab_button[i].setBorderPainted(true);
				break;
			case 12:
				tab_button[i].setForeground(Color.red);
				tab_button[i].addActionListener(new ResetListener());
				tab_button[i].setBorderPainted(false);
				tab_button[i].setFont(opeFont);
				opePan.add(tab_button[i]);
				
				break;
			case 13:
				tab_button[i].addActionListener(new PlusListener());
				tab_button[i].setPreferredSize(dimOpe);
				tab_button[i].setBorderPainted(false);
				tab_button[i].setFont(opeFont);
				opePan.add(tab_button[i]);
				break;
			case 14:
				tab_button[i].addActionListener(new MoinsListener());
				tab_button[i].setPreferredSize(dimOpe);
				tab_button[i].setBorderPainted(false);
				tab_button[i].setFont(opeFont);
				opePan.add(tab_button[i]);
				break;
			case 15:
				tab_button[i].addActionListener(new MultiListener());
				tab_button[i].setPreferredSize(dimOpe);
				tab_button[i].setBorderPainted(false);
				tab_button[i].setFont(opeFont);
				opePan.add(tab_button[i]);
				break;
			case 16:
				tab_button[i].addActionListener(new DivListener());
				tab_button[i].setPreferredSize(dimOpe);
				tab_button[i].setBorderPainted(false);
				tab_button[i].setFont(opeFont);
				opePan.add(tab_button[i]);
				break;
			default:
				numberPan.add(tab_button[i]);
				tab_button[i].setBorderPainted(true);
				tab_button[i].setFont(numberFont);
				tab_button[i].addActionListener(new ChiffreListener());
				break;
			}
		}

		//Set the panels

		panEcran.add(ecran);
		panEcran.setBorder(BorderFactory.createLineBorder(Color.black));

		GridLayout chiffreLayout = new GridLayout(4, 4);
		chiffreLayout.setHgap(15);
		chiffreLayout.setVgap(15);
		GridLayout operateurLayout = new GridLayout(5, 1);
		operateurLayout.setHgap(20);
		operateurLayout.setVgap(20);

		
		//Add the panels
		add(panEcran, BorderLayout.NORTH);
		opePan.setLayout(operateurLayout);
		add(opePan, BorderLayout.EAST);
		numberPan.setLayout(chiffreLayout);
		add(numberPan, BorderLayout.CENTER);

	}

	// method for calculate the different button
	
	/**
	 * Calculate the numbers with the chosen operator
	 * 
	 * 
	 */
	private void calcul() {
		if (operator.equals("+")) {
			result = result + Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(result));
		}
		if (operator.equals("-")) {
			result = result - Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(result));
		}
		if (operator.equals("*")) {
			result = result * Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(result));
		}
		if (operator.equals("/")) {
			try {
				result = result / Double.valueOf(ecran.getText()).doubleValue();
				ecran.setText(String.valueOf(result));
			} catch (ArithmeticException e) {
				ecran.setText("0");
			}
		}
	}

	//Numbers listener
	class ChiffreListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String str = ((JButton) e.getSource()).getText();
			if (update) {
				update = false;
			} else {
				if (!ecran.getText().equals("0"))
					str = ecran.getText() + str;
			}
			if (ecran.getText().length()<20)
			ecran.setText(str);
		
				
		}
	}

	// Equal button listener
	class EgalListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			calcul();
			update = true;
			clicOperateur = false;
		}
	}

	// Addition button listener
	class PlusListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (clicOperateur==true) {
				calcul();
				ecran.setText(String.valueOf(result));
			} else {
				result = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operator = "+";
			update = true;
		}
	}

	// Subtraction button listener
	class MoinsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (clicOperateur==true) {
				calcul();
				ecran.setText(String.valueOf(result));
			} else {
				result = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operator = "-";
			update = true;
		}
	}

	// Multiplication button listener
	class MultiListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (clicOperateur==true) {
				calcul();
				ecran.setText(String.valueOf(result));
			} else {
				result = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operator = "*";
			update = true;
		}
	}

	// Division button listener
	class DivListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (clicOperateur==true) {
				calcul();
				ecran.setText(String.valueOf(result));
			} else {
				result = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operator = "/";
			update = true;
		}
	}

	// Reset button listener 
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			clicOperateur = false;
			update = true;
			result = 0;
			operator = "";
			ecran.setText("");
		}
	}
}
