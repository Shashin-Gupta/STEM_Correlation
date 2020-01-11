package java.eyeLight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Shashin Gupta
 *
 */

public class Coordinator {

	public static final String WILDFIRE_PATH = "java/Wildfire_Information/";
	public static final String HURRICANE_PATH = "java/Hurricane_Information/";
	private static LinkedList<DataSet> cost_hurricanes, cost_wildfires;
	private static int[][] years;
	private static int[][] data;
	private static FileInterpreter[] fis;

	static {
		cost_hurricanes = new LinkedList<DataSet>();
		cost_wildfires = new LinkedList<DataSet>();
		fis = new FileInterpreter[8];
		fis[0] = new FileInterpreter(WILDFIRE_PATH + "Costs/");
		fis[1] = new FileInterpreter(HURRICANE_PATH + "Costs/");
		years = new int[8][]; // The number eight is just an approximation. This may be changed later depending on how much more/less we use
		data = new int[8][];
		
		// Soon to be implemented with the text files
		/**
		fis[2] = new FileInterpreter(WILDFIRE_PATH + "");
		fis[3] = new FileInterpreter(HURRICANE_PATH + "");
		fis[4] = new FileInterpreter(WILDFIRE_PATH + "");
		fis[5] = new FileInterpreter(HURRICANE_PATH + "");
		fis[6] = new FileInterpreter(WILDFIRE_PATH + "");
		fis[7] = new FileInterpreter(HURRICANE_PATH + "");
		 **/
	}

	/**
	 * 
	 * Index Dictionary
	 * 
	 * 0 -> Cost for Wildfires
	 * 1 -> Cost for Hurricanes
	 * 
	 */
	
	public static void main(String[] args) {
		cost_wildfires = fis[0].load();
		cost_hurricanes = fis[1].load();
		years[0] = getQuantitativeData(cost_wildfires, true); 
		years[1] = getQuantitativeData(cost_hurricanes, true); 
		data[0] = getQuantitativeData(cost_wildfires, false);
		data[1] = getQuantitativeData(cost_hurricanes, false);
	}

	private static int[] getQuantitativeData(LinkedList<DataSet> data, boolean year) {
		int[] numericalData = new int[data.size()];
		
		Iterator<DataSet> iter = data.iterator();
		
		int i = 0;
		if (year) while(iter.hasNext()) numericalData[i] = iter.next().getYear();
		else while(iter.hasNext()) numericalData[i] = iter.next().getData();
		return numericalData;
	}
	
	private static class FileInterpreter {
		private Scanner sc;

		public FileInterpreter(String path) {
			try {
				sc = new Scanner(new File(path));
			} 
			catch (FileNotFoundException e) {
				System.out.println("File: " + path + "raised FileNotFoundException");

			}
		}

		public LinkedList<DataSet> load() {

			LinkedList<DataSet> tokens = new LinkedList<DataSet>();
			StringTokenizer st;
			String line;
			int year, data;

			sc.nextLine();
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				st = new StringTokenizer(line, " ,.\":;");

				year = Integer.parseInt(st.nextToken());
				data = Integer.parseInt(st.nextToken());
				tokens.add(new DataSet(year, data));
			}

			return tokens;
		}

	}

}
