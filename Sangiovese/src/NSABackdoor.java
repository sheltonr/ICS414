import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.util.Arrays.asList;

import java.util.List;

public class NSABackdoor {
	List<String> watchWords;
	final private List<String> defaultWatchWords = asList("terrorist", "bomb");
	
	public NSABackdoor() {
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
}
