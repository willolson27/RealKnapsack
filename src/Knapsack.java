import java.io.*;
import java.util.*;

public class Knapsack {
	
	
	private static final String ERROR = "Error: File not found";
	private static final String PROMPT = "Please provide a file name";

	public static int knapsackSum(int[] w, int n, int limit) {
		if (w[n] == 0)
			return 0;
		if (w[n] > limit)
			return 0;
		int withLast = knapsackSum(w, n, limit - w[n]);
		int withoutLast = knapsackSum(w, n-1, limit);
		if (withLast > withoutLast)
			return withLast;
		else
			return withoutLast;
		
		
		
	}
	
	
	public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		if (w[n] == 0)
			return 0;
		if (w[n] > limit)
			return 0;
		list.add(w[n]);
		int withLast = knapsackSum(w, n, limit - w[n]);
		int withoutLast = knapsackSum(w, n-1, limit);
		if (withLast > withoutLast)
			return withLast;
		else
			return withoutLast;
		
		
		
	}
	
	
	public static ArrayList<String> readFile(String filename) throws IOException {
		
		ArrayList<String> fileNames = new ArrayList<String>();
		
		BufferedReader inputReader = null;
	    
	    try {
	    	inputReader = new BufferedReader(new FileReader(filename), 1024);
	    }
	    catch (FileNotFoundException e) {
	    	System.out.println(ERROR);
	    	System.exit(0);
	    }
	    	
	   	String line;
	    while ((line = inputReader.readLine()) != null)
	      fileNames.add(line);
	    	
		return fileNames;
		
		
	}
	
	public static void main(String[] args) throws IOException {
		
		ArrayList<String> fileNames;
		Scanner keyboard = new Scanner(System.in);
		if (args[0] != null)
			fileNames = readFile(args[0]);
		else {
			System.out.println(PROMPT);
			fileNames = readFile(keyboard.nextLine());
		}
		for (String f : fileNames)
			System.out.println(f);
		
		
	}
	
	
	
	
}
