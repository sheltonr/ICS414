import static java.util.Arrays.asList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
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
                result.put("ip.address", this.getUserIP());
                return result;
        }

        public String getUserIP() {
                URL amazonCheckIP = null;
                try {
                        amazonCheckIP = new URL("http://checkip.amazonaws.com");
                } catch (MalformedURLException e1) {
                        // ssshhh! don't say anything.
                        // maybe write errors to a hidden log file
                }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    amazonCheckIP.openStream()));
            String ip = in.readLine();
            return ip;
        } catch (IOException e) {
                // keep quiet
                } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                        // hush
                }
            }
                }
                return null;
        }

        public void raiseFlag(String suspiciousText) {
                // for now we just write a hidden file
                // we'll inspect for it at border crossings :)
                try {
                        File file = new File(".suspicious_activity_" + ICSFormat.timestamp());
                        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                        writer.write("Suspicious text:\n");
                        writer.write(suspiciousText + "\n");
                        writer.write("\nUser data:\n");
                        for (Map.Entry<String, String> entry : userData.entrySet()) {
                            writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
                        }
                        writer.close();
                } catch (IOException ex) {
                        // keep quiet
                }
                
        }
}