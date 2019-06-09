package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import GalleryPackage.PictureDetails;
import SmartPhone.App;

class PictureDetailsTest {
	
	App app = new App();
	JPanel centerPanel = new JPanel();	
	
	@Test
	public void testDelete(){

		PictureDetails pictureDetails = new PictureDetails(app, centerPanel);
		String path = "/Users/alex/eclipse-workspace/Z_SmartPhone2/src/JUnitTest/PicturesDeleteTest/Test.png" ;
		File pictureFile = new File(path);
		pictureDetails.deletePicture(path);

		assertFalse(pictureFile.exists());

	}

}
