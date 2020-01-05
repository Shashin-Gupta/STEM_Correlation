package java.eyeLight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileInterpreter {

	private Scanner sc;
	
	public FileInterpreter(String path) {
		try {
			sc = new Scanner(new File(path));
		} 
		catch (FileNotFoundException e) {
			System.out.println("File: " + path + "raised FileNotFoundException");
			
		}
		load();
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
