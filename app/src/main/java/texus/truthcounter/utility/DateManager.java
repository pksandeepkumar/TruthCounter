package texus.truthcounter.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateManager {

	Date date;
	Calendar cal = null;

	public DateManager(String dateString) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
		date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date != null) {
			cal = Calendar.getInstance();
			cal.setTime(date);
		}
		
	}
	
	public String getDayNameDD_MM_YYY__HHMMAM() {
		if(cal == null) {
			return "";
		}
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if(hour >= 12) {
			hour = hour - 12;
		}
		int minute = cal.get(Calendar.MINUTE);
		int am_pm = cal.get(Calendar.AM_PM);

		int day = cal.get(Calendar.DAY_OF_WEEK);
		int month = cal.get(Calendar.MONTH);
		int day_of_month = cal.get(Calendar.DAY_OF_MONTH);

		String am_pm_string = (am_pm == Calendar.AM) ? "AM" : "PM";
		
		return (getDayName(day) + " " + setPadding(day_of_month) 
				+"-" + setPadding(month + 1) + "-" + cal.get(Calendar.YEAR) + " "
				+ setPadding(hour) + ":" + setPadding(minute) + " " + am_pm_string);
	}

	
	private String setPadding(int num) {

		return (num < 10) ? "0" + num : "" + num;
	}
	
	private String getDayName(int day) {

		switch (day) {
		case Calendar.SUNDAY:
			return "Sunday";
		case Calendar.MONDAY:
			return "Monday";
		case Calendar.TUESDAY:
			return "Tuseday";
		case Calendar.WEDNESDAY:
			return "Wednesday";
		case Calendar.THURSDAY:
			return "Thursday";
		case Calendar.FRIDAY:
			return "Friday";
		case Calendar.SATURDAY:
			return "Saturday";
		}

		return "Sunday";
	}

	private String getMonthName(int month) {

		switch (month) {
		case Calendar.JANUARY:
			return "January";
		case Calendar.FEBRUARY:
			return "February";
		case Calendar.MARCH:
			return "March";
		case Calendar.APRIL:
			return "April";
		case Calendar.MAY:
			return "May";
		case Calendar.JUNE:
			return "June";
		case Calendar.JULY:
			return "July";
		case Calendar.AUGUST:
			return "August";
		case Calendar.SEPTEMBER:
			return "September";
		case Calendar.OCTOBER:
			return "October";
		case Calendar.NOVEMBER:
			return "November";
		case Calendar.DECEMBER:
			return "December";
		}

		return "January";
	}
	
	
	
}
