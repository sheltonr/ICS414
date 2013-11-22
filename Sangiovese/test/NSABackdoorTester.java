import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

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
		assertTrue(bd.userData instanceof Map);
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
	
	@Test
	public void testGetUserData() {		
		Map<String, String> results = bd.getUserData();
		String expectedName = System.getProperty("user.name");
		assertTrue(results.containsKey("user.name"));
		assertEquals(expectedName, results.get("user.name"));
		
		String expectedLanguage = System.getProperty("user.language");
		assertTrue(results.containsKey("user.language"));
		assertEquals(expectedLanguage, results.get("user.language"));
		
		String expectedCountry = System.getProperty("user.country");
		assertTrue(results.containsKey("user.country"));
		assertEquals(expectedCountry, results.get("user.country"));
		
		String expectedTimeZone = System.getProperty("user.timezone");
		assertTrue(results.containsKey("user.timezone"));
		assertEquals(expectedTimeZone, results.get("user.timezone"));
		
		String expectedOS = System.getProperty("os.name");
		assertTrue(results.containsKey("os.name"));
		assertEquals(expectedOS, results.get("os.name"));		
	}
	
	@Test
	public void testGetUserIP() {
		String resultIP = bd.getUserIP();		
        // TODO figure out how to unit test this
        assertEquals(0, 0);
	}

}
