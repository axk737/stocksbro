package stocksbro;

import java.util.*;

public class StockProcessor {

	private ArrayList<Double> srs; // ArrayList of supports & resistances
	private ArrayList<Double> srlines; // ArrayList of S/R lines
	
	/* Constructor */
	public StockProcessor(String symbol) {
		
		// This start date can be manually modified if needed.
		GregorianCalendar startdate = new GregorianCalendar(1970, 0, 1);
		try {
			StockDownloader data = new StockDownloader(symbol, startdate);
			srs = new ArrayList<Double>();
			
			// Find minimas
			ArrayList<Double> lowsarraylist = data.getLows();
			Double[] lows = new Double[lowsarraylist.size()];
			lows = lowsarraylist.toArray(lows);
			
			/* Compare data point to 2 points to the left and 2 to the right
			 * if it is lower than the 4, it is considered a minima */
			for (int i = 2; i < (lows.length - 2); i++) {
				if (lows[i] < lows[i-1] && lows[i] < lows[i-2] && 
						lows[i] < lows[i+1] && lows[i] < lows[i+2]) {
					srs.add(lows[i]);
				}
			}
			
			// Find maximas
			ArrayList<Double> highsarraylist = data.getHighs();
			Double[] highs = new Double[highsarraylist.size()];
			highs = highsarraylist.toArray(highs);
			
			/* Compare data point to 2 points to the left and 2 to the right
			 * if it is high than the 4, it is considered a maxima */
			for (int i = 2; i < (highs.length - 2); i++) {
				if (highs[i] < highs[i-1] && highs[i] < highs[i-2] && 
						highs[i] < highs[i+1] && highs[i] < highs[i+2]) {
					srs.add(highs[i]);
				}
			}
			
			/* Filter out irrelevant past data by removing any pivots that are
			 * more than 1.5x the latest closing price or lower than half */
			double latestclose = data.getLatestClose();
			for (int i = 0; i < srs.size(); i++) {
				if (srs.get(i) > 1.5 * latestclose || 
						srs.get(i) < latestclose / 2) {
					srs.remove(i);
					i--;
				}
			}
			
			// S/Rs are calculated by processing elements in srs with KMean
			KMean kmean = new KMean(8, srs);

			/* Output */
			Gui.Output.setText("<html>*** OUTPUT ***<br>"
					+ "Latest closing price: " + latestclose + "</html>");
			kmean.print();
			Gui.lblWarning.setText("<html>WARNING: supports and resistances "
					+ "calculated are averages of clustered turning points "
					+ "from " + symbol.toUpperCase() + "'s data. "
					+ "Refer to its chart for optimal buy/sell prices.</html>");
			Gui.Link.setText("https://www.tradingview.com/"
					+ "chart/?symbol=" + symbol.toUpperCase());
		} catch (Exception e) {
			Gui.lblConfirmation.setText("<html>Unable to process data. "
					+ "<br>Please check symbol or report to Sea.</html>");
			Gui.lblWarning.setText("");
			Gui.Output.setText("");
			Gui.SR.setText("");
			Gui.Link.setText("");
		}
	}
}
