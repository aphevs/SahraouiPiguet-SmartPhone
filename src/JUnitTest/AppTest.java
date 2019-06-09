package JUnitTest;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import SmartPhone.App;

class AppTest {
	
	private JPanel panel1, panel2;
	private App app;

	@Test
	public void show()
	{		
		app = new App();
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		app.addCard(panel1, "panel");
		app.addCard(panel2, "panel2");
		
		app.showCard("panel");
		
		assertFalse(panel2.isVisible());
		assertTrue(panel1.isVisible());
	}

}
