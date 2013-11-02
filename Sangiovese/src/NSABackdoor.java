import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NSABackdoor {
	List<String> watchWords;
	Map<String, String> userData;
	final private List<String> defaultWatchWords = asList("terrorist", "bomb");
	
	public NSABackdoor() {
		this.userData = this.getUserData();
		try {
			this.watchWords = this.readWatchWordsFile();
		} catch (IOException e) {
			this.watchWords = defaultWatchWords;
		}
	}

	ArrayList<String> readWatchWordsFile() throws IOException {
		ArrayList<String> words = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(".watchwords"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			words.add(line.trim().toLowerCase());
		}
		reader.close();
		return words;
	}

	public boolean stringIsSuspicious(String inputString) {
		for (String w : this.watchWords) {
			if (inputString.contains(w)) {
				return true;
			}
		}
		return false;
	}

	public Map<String, String> getUserData() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("user.name", System.getProperty("user.name"));
		result.put("user.language", System.getProperty("user.language"));
		result.put("user.country", System.getProperty("user.country"));
		result.put("user.timezone", System.getProperty("user.timezone"));
		result.put("os.name", System.getProperty("os.name"));		
		return result;
	}
}
