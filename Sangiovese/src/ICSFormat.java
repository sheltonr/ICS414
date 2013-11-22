import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  ICSFormat Class
 *  -A static class. Provides data checks and reformats syntax.
 *
 *  Functions
 *  -ICSFormat()			|	private to prevent instantiation. 
 *  -valid(text)			|	filters text for whitelisted characters.
 *  -valid(date, time)			|	checks date and time is valid.
 *  -timestamp()			|	produces an rfc 5545 timestamp.
 *  -compare(smallerDate, biggerDate)	|	checks smaller date is smaller than bigger date.
 *  
 *  8/10/2013
 */

public class ICSFormat{
	/**
	 * Private Contructor for .ics FileMaker class.
	 */
	private ICSFormat(){
	}

	/**
	 * Checks that text has no dangerous characters
	 * @param value - String of text
	 * @return value - input string or empty string
	 */
        public static String valid(String value){
            if(value.matches("^[0-9a-zA-Z\\.,?!_/\\s]{1,}$"))	//whitelist valid characters a-z, 0-9, ._/?!, space 
                return value;
            return "";
        }

	/**
	 * Checks that both date and time are valid.
	 * @param date - String of date in syntax, "MM/dd/yyyy"
	 * @param time - String of time in syntax, "HH:mm:ss"
	 * @return datetime - the rfc 5545 syntax or empty string.
	 */
	public static String valid(String date, String time){
		SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");//input from gui
		SimpleDateFormat outputFormat= new SimpleDateFormat("yyyyMMdd'T'HHmmss");  //output for rfc5545
		inputFormat.setLenient(false); 			//catch if invalid time is given
		time = (time.length()==5)?time+":00":time; 	//if seconds aren't given
		try{
			Date formattedDate = inputFormat.parse(date+" "+time);
			return (outputFormat.format(formattedDate));
		}catch (Exception ex){
		}
		return "";
	}

	/**
	 * Generates current system timestamp
	 * @return datetime - the rfc 5545 syntax.
	 */
	public static String timestamp(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return valid(dateFormat.format(date),timeFormat.format(date));
	}

	/**
	 * Checks that first Input is less than second Input
	 * @param firstInput - String of datetime in rfc 5545 syntax
	 * @param secondInput - String of datetime in rfc 5545 syntax
	 * @return int - negative numbers are error codes. positive means firstInput is less than secondInput
	 */
	public static int compare(String firstInput, String secondInput){
		if(firstInput==""||secondInput=="") return -1; 	//one of the inputs is bad
		try{
			String[] first = firstInput.split("T");
			String[] second = secondInput.split("T");

			if(Integer.parseInt(first[0])>Integer.parseInt(second[0]))	//because first date is bigger,
				return -2;						//its bad. because of date 
			if(Integer.parseInt(first[0])<Integer.parseInt(second[0]))	//because second date is bigger
				return 1;						//its good

			if(Integer.parseInt(first[1])>=Integer.parseInt(second[1]))	//because first time is bigger, and date is same,
				return -3;						//its bad. because of time
			if(Integer.parseInt(first[1])<Integer.parseInt(second[1]))	//because second time is bigger, and date is same,
				return 1;						//its good
		}catch (Exception ex){
		}
		return -1;
	}
}
