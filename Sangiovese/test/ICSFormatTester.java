import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;


public class ICSFormatTester {
	
	@Test
	public void testCompare() {
		String start = "00000000T000000";
		String end = "00000000T000001";
		assertTrue(ICSFormat.compare(start, end) == 1);
	}
	
	@Test
	public void testValid() {
		String date1 = "20131203T125213";
		String date2 = ICSFormat.valid("12/3/2013", "12:52:13");
		assertTrue(date1.equals(date2));
	}
	
	@Test
	public void testTimeStamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
		Date date = new Date();
		String date1 = sdf.format(date);
		String date2 = ICSFormat.timestamp();
		assertTrue(date1.equals(date2));	
	}
}
