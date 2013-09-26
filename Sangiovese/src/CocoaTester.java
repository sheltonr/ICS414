import java.io.File;

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
		if(file.exists()) {
			org.junit.Assert.assertTrue(file.delete());
		}
		
		FileMaker test = new FileMaker();
		test.set_attribute("VERSION","1.0","calendar");
		test.set_attribute("CALSCALE","GREGORIAN","calendar");
		test.set_attribute("SUMMARY","Exam Study");
		test.set_attribute("DESCRIPTION","study for exam information and details");
		test.set_attribute("LOCATION","Hamilton Library");
		
		//file generated
		org.junit.Assert.assertTrue(test.generate(fileName));
		
		//file created
		org.junit.Assert.assertTrue(file.exists());
		
		//second test
		iCalendarGUI gui = new iCalendarGUI();
		
	    org.junit.Assert.assertTrue(gui.generate());  
	       
	}
}

