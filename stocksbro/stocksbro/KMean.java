package stocksbro;

import static java.lang.Math.abs;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class KMean {

    private ArrayList<Double> dataItems;
    private ArrayList<Double> cz;
    private ArrayList<Double> oldCz;
    private ArrayList<Double> row;
    private ArrayList<ArrayList<Double>> groups;

    /* KMean takes the pivots and group them into K clustered points.
     * For fairly accurate support/resistance lines, I've decided to use
     * K = 8; therefore there will be 8 S/R lines calculated.
     * Each S/R line will also have a 'strength' value.
     * The stronger the line, the harder it is to be broken through. */
    
    public KMean(int k, ArrayList<Double> srs) {
        dataItems = new ArrayList<>();
        cz = new ArrayList<>();
        oldCz = new ArrayList<>();
        row = new ArrayList<>();
        groups = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            groups.add(new ArrayList<>());
        }

        for (int i = 0; i < srs.size(); i++) {
            dataItems.add(srs.get(i));
            if (i < k) {
                cz.add(dataItems.get(i));
            }
        }
        int iter = 1;
        do {
            for (double aItem : dataItems) {
                for (double c : cz) {
                    row.add(abs(c - aItem));
                }
                groups.get(row.indexOf(Collections.min(row))).add(aItem);
                row.removeAll(row);
            }
            for (int i = 0; i < k; i++) {
                if (iter == 1) {
                    oldCz.add(cz.get(i));
                } else {
                    oldCz.set(i, cz.get(i));
                }
                if (!groups.get(i).isEmpty()) {
                    cz.set(i, average(groups.get(i)));
                }
            }
            if (!cz.equals(oldCz)) {
                for (int i = 0; i < groups.size(); i++) {
                    groups.get(i).removeAll(groups.get(i));
                }
            }
            iter++;
        } while (!cz.equals(oldCz));
    }

    public static double average(ArrayList<Double> list) {
        double sum = 0;
        for (Double value : list) {
            sum = sum + value;
        }
        return sum / list.size();
    }
    
    // print method
    public void print() {
    	String line = "<html>";
    	DecimalFormat dc = new DecimalFormat("###,###.00");
    	
    	for (int i = 0; i < groups.size(); i++) {
    		int strength = groups.get(i).size();
        	double sum = 0;
        	double SR = 0;
        	for(double d : groups.get(i)) {
        	    sum += d;
        	}
        	SR = sum / groups.get(i).size();
        	String StringSR = dc.format(SR);
        	line = line + "S/R Line " + (i + 1) + ": ";
        	line = line + StringSR + " with Strength of " + strength + "<br>";
        }
    	line = line + "</html>";
    	Gui.SR.setText(line);
    }
}