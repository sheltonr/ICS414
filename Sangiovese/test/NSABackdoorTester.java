import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class NSABackdoorTester {
	NSABackdoor bd;

	@Before
	public void setUp() throws Exception {
		bd = new NSABackdoor();
	}

	@Test
	public void testConstructor() {
		assertTrue(bd instanceof NSABackdoor);
		assertTrue(bd.watchWords instanceof List);
		assertTrue(bd.watchWords.size() > 0);
	}
	
	@Test
	public void testReadWatchWordsFile() throws IOException {
		List<String> words = bd.readWatchWordsFile();
		assertTrue(words.size() > 0);
		assertTrue(words.contains("cloud"));
		assertTrue(words.contains("pork"));
		assertTrue(words.contains("sleet"));		
	}
	
	@Test
	public void testStringIsSuspicious() {
		assertTrue(bd.stringIsSuspicious("man i love pork a whole lot"));
		assertTrue(bd.stringIsSuspicious("looks like a cloudy day"));
		assertFalse(bd.stringIsSuspicious("none of these words is suspicious"));
	}

}
