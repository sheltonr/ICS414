import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class GUITester {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
	
	@Before
	public void setUp() {
		iCalendarGUI.generate();
		iCalendarGUI.setRecurring(true);
	}
	
	@Test
	public void testTitle() {
		assertTrue(iCalendarGUI.getTitle().equals(""));
		iCalendarGUI.setTitle("Party");
		assertTrue(iCalendarGUI.getTitle().equals("Party"));
	}
	
	@Test
	public void testLocation() {
		assertTrue(iCalendarGUI.getLoc().equals(""));
		iCalendarGUI.setLoc("At the haunted house!");
		assertTrue(iCalendarGUI.getLoc().equals("At the haunted house!"));	
	}
	
	@Test
	public void testDescription() {
		assertTrue(iCalendarGUI.getDescription().equals(""));
		iCalendarGUI.setDescription("Getting crunk tonight...");
		assertTrue(iCalendarGUI.getDescription().equals("Getting crunk tonight..."));
	}
	
	@Test
	public void testComment() {
		assertTrue(iCalendarGUI.getComment().equals(""));
		iCalendarGUI.setComment("I hope they have a pool");
		assertTrue(iCalendarGUI.getComment().equals("I hope they have a pool"));
	}
	
	@Test 
	public void testClassification() {
		iCalendarGUI.setClassification(1);
		assertTrue(iCalendarGUI.getClassification().equals("Private"));
		iCalendarGUI.setClassification(0);
		assertTrue(iCalendarGUI.getClassification().equals("Public"));
		iCalendarGUI.setClassification(2);
		assertTrue(iCalendarGUI.getClassification().equals("Confidential"));
	}
	
	@Test
	public void testStartDate() {
		String date = sdf.format(Calendar.getInstance().getTime());
		assertTrue(iCalendarGUI.getStartDate().equals(date));
	}
	
	@Test
	public void testEndDate() {
		String date = sdf.format(Calendar.getInstance().getTime());
		assertTrue(iCalendarGUI.getEndDate().equals(date));
	}
	
	@Test
	public void testStartTime() {
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 1);
        cal.set(Calendar.MINUTE, 0);
        String time = stf.format(cal.getTime());
        assertTrue(iCalendarGUI.getStartTime().equals(time));
	}
	
	@Test
	public void testEndTime() {
		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 2);
        cal.set(Calendar.MINUTE, 0);
        String time = stf.format(cal.getTime());
        assertTrue(iCalendarGUI.getEndTime().equals(time));
	}
	
	@Test
	public void testRecurring() {
		assert(iCalendarGUI.recurring());
	}
	
	@Test 
	public void testRepeat() {
		iCalendarGUI.setRepeat(3);
		assertTrue(iCalendarGUI.getRepeat().equals("Yearly"));
		iCalendarGUI.setRepeat(1);
		assertTrue(iCalendarGUI.getRepeat().equals("Weekly"));
		iCalendarGUI.setRepeat(2);
		assertTrue(iCalendarGUI.getRepeat().equals("Monthly"));
		iCalendarGUI.setRepeat(0);
		assertTrue(iCalendarGUI.getRepeat().equals("Daily"));
	}
	
	@Test
	public void testUntilDate() {
		String date = sdf.format(Calendar.getInstance().getTime());
		assertTrue(iCalendarGUI.getUntilDate().equals(date));
	}
	
	@Test
	public void testExceptionDate() {
		String date = sdf.format(Calendar.getInstance().getTime());
		assertTrue(iCalendarGUI.getExceptionDate().equals(date));
	}
}
