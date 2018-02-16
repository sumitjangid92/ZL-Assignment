package utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HelperFunctions {

	public String GetCurrentDateTime ()
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dateobj = new Date();
		return(df.format(dateobj).toString());
	}

	public String addDaysInCurrentDate(int days ) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dateobj = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dateobj);
		c.add(Calendar.DATE, days); 
		dateobj = c.getTime();
		return(df.format(dateobj).toString());
	}

}
