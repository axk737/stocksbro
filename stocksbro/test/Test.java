package test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {

	public static void main(String[] args) {
		String symbol = "dxy";
		GregorianCalendar startdate = new GregorianCalendar(1970, 0, 1);
		GregorianCalendar enddate = new GregorianCalendar();
		String startmonth = new DateFormatSymbols().getMonths()
				[startdate.get(Calendar.MONTH)];
		String endmonth = new DateFormatSymbols().getMonths()
				[enddate.get(Calendar.MONTH)];
		String link = "http://www.google.com/finance/historical?q=" + symbol + 
				"&startdate=" + 
				startmonth + " " + 
				startdate.get(Calendar.DAY_OF_MONTH) + ", " + 
				startdate.get(Calendar.YEAR) + 
				"&enddate=" + 
				endmonth + " " + 
				enddate.get(Calendar.DAY_OF_MONTH) + ", " + 
				enddate.get(Calendar.YEAR) + 
				"&output=csv";
		
		System.out.println(link);

	}

}
