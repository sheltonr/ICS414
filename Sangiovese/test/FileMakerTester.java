import static org.junit.Assert.assertTrue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

public class FileMakerTester {
	private File file;
	String fileName;
	FileMaker fm;
	String[] testStrings = {"LOCATION", "DESCRIPTION", "CLASS", "SUMMARY"};
	
	@Before
	public void setUp() throws Exception{
		fileName = "test";
		fm = new FileMaker();
	}
	
	@Test
	public void testConstructor() {
		assertTrue(fm instanceof FileMaker);
	}
	
	@Test
	public void testFileMaker() throws IOException {
		for (String str : testStrings) {
			fm.set_attribute(str, "test");
		}
		
		file = (fm.generate(fileName));
		BufferedReader br = new BufferedReader(new FileReader(file));
		assertTrue(br.readLine().equals("BEGIN:VCALENDAR"));
		assertTrue(br.readLine().equals("VERSION:2.0"));
		assertTrue(br.readLine().equals("CALSCALE:GREGORIAN"));
		assertTrue(br.readLine().equals("BEGIN:VEVENT"));
		for (String str : testStrings) {
			assertTrue((str + ":test").equals(br.readLine()));
		}
		br.close();
	}
}



