import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class GUITester {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
    iCalendarGUI gui;
	
	@Before
	public void setUp() {
		gui = new iCalendarGUI();
		gui.generate();
		gui.setRecurring(true);
	}
	
	@Test
	public void testConstructor() {
		assertTrue(gui instanceof iCalendarGUI);
	}
	
	@Test
	public void testTitle() {
		assertTrue(gui.getTitle().equals(""));
		gui.setTitle("Party");
		assertTrue(gui.getTitle().equals("Party"));
	}
	
	@Test
	public void testLocation() {
		assertTrue(gui.getLoc().equals(""));
		gui.setLoc("At the haunted house!");
		assertTrue(gui.getLoc().equals("At the haunted house!"));	
	}
	
	@Test
	public void testDescription() {
		assertTrue(gui.getDescription().equals(""));
		gui.setDescription("Getting crunk tonight...");
		assertTrue(gui.getDescription().equals("Getting crunk tonight..."));
	}
	
	@Test
	public void testComment() {
		assertTrue(gui.getComment().equals(""));
		gui.setComment("I hope they have a pool");
		assertTrue(gui.getComment().equals("I hope they have a pool"));
	}
	
	@Test 
	public void testClassification() {
		gui.setClassification(1);
		assertTrue(gui.getClassification().equals("Private"));
		gui.setClassification(0);
		assertTrue(gui.getClassification().equals("Public"));
		gui.setClassification(2);
		assertTrue(gui.getClassification().equals("Confidential"));
	}
	
	@Test
	public void testStartDate() {
		String date = sdf.format(Calendar.getInstance().getTime());
		assertTrue(gui.getStartDate().equals(date));
	}
	
	@Test
	public void testEndDate() {
		String date = sdf.format(Calendar.getInstance().getTime());
		assertTrue(gui.getEndDate().equals(date));
	}
	
	@Test
	public void testStartTime() {
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);
        cal.set(Calendar.MINUTE, 0);
        String time = stf.format(cal.getTime());
        assertTrue(gui.getStartTime().equals(time));
	}
	
	@Test
	public void testEndTime() {
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 2);
        cal.set(Calendar.MINUTE, 0);
        String time = stf.format(cal.getTime());
        assertTrue(gui.getEndTime().equals(time));
	}
	
	@Test
	public void testRecurring() {
		assert(gui.recurring());
	}
	
	@Test 
	public void testRepeat() {
		gui.setRepeat(3);
		assertTrue(gui.getRepeat().equals("Yearly"));
		gui.setRepeat(1);
		assertTrue(gui.getRepeat().equals("Weekly"));
		gui.setRepeat(2);
		assertTrue(gui.getRepeat().equals("Monthly"));
		gui.setRepeat(0);
		assertTrue(gui.getRepeat().equals("Daily"));
	}
	
	@Test
	public void testForever() {
		gui.setForever(true);
		assertTrue(gui.forever());
		assertFalse(gui.repeatState());
	}
	
	@Test
	public void testUntilDate() {
		String date = sdf.format(Calendar.getInstance().getTime());
		assertTrue(gui.getUntilDate().equals(date));
	}
	
	@Test
	public void testExceptionDate() {
		String date = sdf.format(Calendar.getInstance().getTime());
		assertTrue(gui.getExceptionDate().equals(date));
	}
	
	@Test
	public void testGenerate() throws FileNotFoundException {
		String[] nameArray = gui.getTitle().replaceAll("\\[([^\\]]+)\\]", "").split(" ");
        String name = ICSFormat.timestamp();
        name += nameArray[0];
        gui.clickGenerate();
        File file = new File(System.getProperty("user.dir") + "/" + name + ".ics");
        assertTrue(file.exists());
        assertTrue(file.delete());
	}
}
