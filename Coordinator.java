package java.eyeLight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Coordinator {

	public static final String WILDFIRE_PATH = "java/Wildfire_Information/";
	public static final String HURRICANE_PATH = "java/Hurricane_Information/";
	private static LinkedList<String> cost_hurricanes, cost_wildfires;
	private static int[][] years;
	private static FileInterpreter[] fis;
	
	static {
		cost_hurricanes = new LinkedList<String>();
		cost_wildfires = new LinkedList<String>();
		fis = new FileInterpreter[8];
		fis[0] = new FileInterpreter(WILDFIRE_PATH + "Costs/");
		fis[1] = new FileInterpreter(HURRICANE_PATH + "Costs/");
		years = new int[8][];
		
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
	
	public static void main(String[] args) {
		cost_wildfires = fis[0].load();
		cost_hurricanes = fis[1].load();
		
		
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
		
		public LinkedList<String> load() {
			
			LinkedList<String> tokens = new LinkedList<String>();
			StringTokenizer st;
			String line;
			
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				st = new StringTokenizer(line, " ,.\":;");
				
				while (st.hasMoreTokens()) tokens.add(st.nextToken());
				
			}
			
			return tokens;
		}
		
	}

	
}
