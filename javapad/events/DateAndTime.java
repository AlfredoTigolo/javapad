package javapad.events;

import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

/**

*/
public class DateAndTime {

	static Date date = new Date();
	static Date time = new Date();
	static String dateOut, timeOut;
	static DateFormat dateFormatter, timeFormatter;

	/**
		Static method that calls both date and time methods
	*/
	static public String BOTH() {
		displayTime();
		System.out.print(" ");
		displayDate();
		return (" "+timeOut +" "+dateOut+" ");
	}

	/**
		Static method that displays the date
	*/
	static public void displayDate() {
		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.US);
		dateOut = dateFormatter.format(date);
		//System.out.print(dateOut+" ");
	}

	/**
		Static method that displays the time
	*/
	static public void displayTime() {
		timeFormatter = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.US);
		timeOut = timeFormatter.format(time);
		//System.out.print(timeOut+" ");
	}

	/**
	//Main Method used to test the class
	public static void main(String[] args) {
		DateAndTime.BOTH();
	}
	*/
}