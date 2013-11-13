import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *  FileMaker Class
 *  -Stores attribute/value pairs.
 *  -Writes syntax to .ics file.
 *  
 *  Global Vars
 *  -private event_attributes
 *  -private calendar_attributes
 *  
 *  Functions
 *  -FileMaker()			        |	creates hash maps
 *  -set_attribute(key,value)	    |	adds entry, overwrites existing entries, for event attrs
 *  -set_attribute(key,value,type)	|	adds entry, specifically add event or calendar attrs
 *  -remove_attribute(key)		    |	removes entry, for event attrs
 *  -remove_attribute(key,type)	    |	removes entry, specifically for event or calendar attrs
 *  -generate_content()		        |	generates .ics syntax
 *  -generate()		                |	writes content to .ics file
 *  
 *  8/2/2013
 */

public class FileMaker {
	private LinkedHashMap<String, String> event_attributes;
	private LinkedHashMap<String, String> calendar_attributes;
	
	/**
	 * Contructor for .ics FileMaker class.
	 */
	public FileMaker() {
		event_attributes = new LinkedHashMap<String, String>();
		calendar_attributes = new LinkedHashMap<String, String>();
	}
	
	/**
	 * Sets attribute of calendar or event.
	 * @param key - Key to be set.
	 * @param value - Value of key.
	 * @return public int set_attribute(key, value, "event")
	 */
	public int set_attribute(String key, String value) {
		return set_attribute(key, value, "event");
	}
	
	/**
	 * Sets attribute of calendar or event. 
	 * @param key - Key to be set.
	 * @param value - Value of key.
	 * @param type - Type of key (calendar or event).
	 * @return 1 on success.
	 * 		   0 on failure.
	 */
	public int set_attribute(String key, String value, String type) {
		if (type.equals("event")) {
			event_attributes.put(key, value);
		} else if (type.equals("calendar")) {
			calendar_attributes.put(key, value);
		}
		return 0;
	}

	public int remove_attribute(String key) {
		return remove_attribute(key, "event");
	}
	
	/**
	 * removes attribute from calendar or event.
	 * @param key - Key to be removed.
	 * @param type - Type of key (calendar or event).
	 * @return 1 on success.
	 * 		   0 on failure.
	 */
	public int remove_attribute(String key, String type) {
		if (type.equals("event")) {
			if (event_attributes.containsKey(key)) {
				event_attributes.remove(key);
				return 1;
			}
		} else if (type.equals("calendar")) {
			if (calendar_attributes.containsKey(key)) {
				calendar_attributes.remove(key);
				return 1;
			}
		}
		return 0;
	}
	
	/**
	 * Generates the content of the .ics file to be written.
	 * @return String of the content.
	 */
	@SuppressWarnings("rawtypes")
	public String generate_content() {
		String content = "";
		String tags_calendar_header = "BEGIN:VCALENDAR\r\n";
		String tags_event_header = "BEGIN:VEVENT\r\n";
		String tags_footer = "END:VEVENT\r\nEND:VCALENDAR";
		
		content += (tags_calendar_header);
		
		Set calendarSet = calendar_attributes.entrySet();
	    Iterator i = calendarSet.iterator();

	    while (i.hasNext()) {
	    	Map.Entry me = (Map.Entry)i.next();
			content += (me.getKey() + ":" + me.getValue() + "\r\n");
		}

		content += (tags_event_header);
		
		Set eventSet = event_attributes.entrySet();
		i = eventSet.iterator();
		
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry)i.next();
			content += (me.getKey() + ":" + me.getValue() + "\r\n");
		}

		content += (tags_footer);
		return content;
	}
	
	/** 
	 * Generates file with the the given filename.
	 * @param fileName - Name of the file to be written. 
	 * @return true on success.
	 *		   false on failure.
	 */
	public File generate(String fileName) {
		try {
			File file = new File(fileName + ".ics");
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write(generate_content());
			writer.close();
			return file;
		} catch (IOException ex) {
			System.out.print(ex + "fail");
			return null;
		}
		
	}
}