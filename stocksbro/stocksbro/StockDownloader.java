package stocksbro;

import java.util.*;
import java.net.*;
import java.text.DateFormatSymbols;

public class StockDownloader {
	public static final int DATE = 0;
	public static final int OPEN = 1;
	public static final int HIGH = 2;
	public static final int LOW = 3;
	public static final int CLOSE = 4;
	public static final int VOLUME = 5;
	
	private ArrayList<GregorianCalendar> dates;
	private ArrayList<Double> opens;
	private ArrayList<Double> highs;
	private ArrayList<Double> lows;
	private ArrayList<Double> closes;
	private ArrayList<Integer> volumes;
	
	public StockDownloader(String symbol, GregorianCalendar startdate) {
		
		/* Initializes arraylists right here. */
		dates = new ArrayList<GregorianCalendar>();
		opens = new ArrayList<Double>();
		highs = new ArrayList<Double>();
		lows = new ArrayList<Double>();
		closes = new ArrayList<Double>();
		volumes = new ArrayList<Integer>();
		
		/* URL constructed by the arguments passed into StockDownloader */
		GregorianCalendar enddate = new GregorianCalendar();
		String startmonth = new DateFormatSymbols().getMonths()
				[startdate.get(Calendar.MONTH)];
		String endmonth = new DateFormatSymbols().getMonths()
				[enddate.get(Calendar.MONTH)];
		String link = "http://www.google.com/finance/historical?q=" + symbol + 
				"&startdate=" + 
				startmonth + "%20" + 
				startdate.get(Calendar.DAY_OF_MONTH) + ",%20" + 
				startdate.get(Calendar.YEAR) + 
				"&enddate=" + 
				endmonth + "%20" + 
				enddate.get(Calendar.DAY_OF_MONTH) + ",%20" + 
				enddate.get(Calendar.YEAR) + 
				"&output=csv";
		
		try {
			URL url = new URL(link);
			URLConnection data = url.openConnection();
			Scanner input = new Scanner(data.getInputStream());
			int mm = 0, dd = 0, yyyy = 0;
		
			
			if (input.hasNext()) { // skips header
				input.nextLine();
			}

			while (input.hasNextLine()) {
				String line = input.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				
			
				// Processes the date field
				String date = tokenizer.nextToken();
				String open = tokenizer.nextToken();
				String high = tokenizer.nextToken();
				String low = tokenizer.nextToken();
				String close = tokenizer.nextToken();
				String volume = tokenizer.nextToken();
				
				// Tokenize the date field to retrieve dd, mm, yyyy.
				StringTokenizer datetokenizer = new StringTokenizer(date, "-");
				dd = Integer.parseInt(datetokenizer.nextToken());
				switch (datetokenizer.nextToken()) {
		         case "Jan":
		             mm = 0;
		             break;
		         case "Feb":
		        	 mm = 1;
		             break;
		         case "Mar":
		        	 mm = 2;
		             break;
		         case "Apr":
		        	 mm = 3;
		             break;
		         case "May":
		        	 mm = 4;
		             break;
		         case "Jun":
		        	 mm = 5;
		             break;
		         case "Jul":
		        	 mm = 6;
		             break;
		         case "Aug":
		        	 mm = 7;
		             break;
		         case "Sep":
		        	 mm = 8;
		             break;
		         case "Oct":
		        	 mm = 9;
		             break;
		         case "Nov":
		        	 mm = 10;
		             break;
		         case "Dec":
		        	 mm = 11;
		             break;
		         default:
		             throw new IllegalArgumentException("?");
				}
				yyyy = Integer.parseInt(datetokenizer.nextToken()) + 2000;
				
				// if volume = 0, the stock isn't traded that day, so skip.
				if (Integer.parseInt(volume) > 0) {
					dates.add(new GregorianCalendar(yyyy, mm, dd));
					opens.add(Double.parseDouble(open));
					highs.add(Double.parseDouble(high));
					lows.add(Double.parseDouble(low));
					closes.add(Double.parseDouble(close));
					volumes.add(Integer.parseInt(volume));
				}
			}
			
			Gui.lblConfirmation.setText("Retrieved data for " +
			symbol.toUpperCase() + " starting from " +
					(mm + 1) + "/" + dd + "/" + yyyy);
			
		} catch (Exception e) {
			Gui.lblConfirmation.setText("Invalid symbol entered.");
		}
	}
	
	/* get Methods */
	public ArrayList<GregorianCalendar> getDates() {
		return dates;
	}
	public ArrayList<Double> getOpens() {
		return opens;
	}
	public ArrayList<Double> getHighs() {
		return highs;
	}
	public ArrayList<Double> getLows() {
		return lows;
	}
	public ArrayList<Double> getCloses() {
		return closes;
	}
	public ArrayList<Integer> getVolumes() {
		return volumes;
	}
	public double getLatestClose() {
		return closes.get(0);
	}
}
