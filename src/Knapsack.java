import java.io.*;
import java.util.*;

public class Knapsack {
	
	static int[] testArr = {2, 3, 3, 4 , 1};
	private static final String ERROR = "Error: File not found";
	private static final String PROMPT = "Please provide a file name";

	/**
	 * 
	 * @param w
	 * @param n
	 * @param limit
	 * @return
	 */
	public static int knapsackSum(int[] w, int n, int limit) {

		if (n == 0)
			return 0;
		else if (w[n-1] > limit) {
			return knapsackSum(w, n - 1 , limit);
		}
		else
			return w[n] + knapsackSum(w, n-1, limit - w[n]);
		
		
		
	}
	
	/**
	 * 
	 * @param w
	 * @param n
	 * @param limit
	 * @param list
	 * @return
	 */ 
	public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		if (n == 0)
			return 0;
		else if (w[n-1] > limit) {
			return knapsackSum(w, n - 1 , limit);
		}
		else
			return w[n] + knapsackSum(w, n-1, limit - w[n]);
		
		
		
	} 
	
	/**
	 * 
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
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
	
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 */
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
		System.out.println(knapsackSum(testArr, testArr.length - 1, 11));
		
	}
	
	
	
	
}
