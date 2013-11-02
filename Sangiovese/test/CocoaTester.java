import java.io.File;
import org.junit.Assert;

/**
 * Tester class for Team Cocoa's .ics file generator.
 * 
 * 8/1/2013
 */

public class CocoaTester {
	@org.junit.Test
	public void test() {
		//first test
		String fileName = "test";
		File file = new File (System.getProperty("user.dir") + "\\" + fileName + ".ics");
		
		//delete file if it exists
		if (file.exists()) {
			Assert.assertTrue(file.delete());
		}
		
		FileMaker fm = new FileMaker();
		fm.set_attribute("VERSION","1.0","calendar");
		fm.set_attribute("CALSCALE","GREGORIAN","calendar");
		fm.set_attribute("SUMMARY","Exam Study");
		fm.set_attribute("DESCRIPTION","study for exam information and details");
		fm.set_attribute("LOCATION","Hamilton Library");
		
		//file generated
		Assert.assertTrue(fm.generate(fileName));
		
		//file created
//		Assert.assertTrue(file.exists());
		
		//delete newly created file
		if (file.exists()) {
			Assert.assertTrue(file.delete());
		}
		
		//try to generate gui
	    Assert.assertTrue(iCalendarGUI.generate());  
	       
	}
}

