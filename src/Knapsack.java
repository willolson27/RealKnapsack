import java.io.*;
import java.util.*;
/**
 * 
 * @author Will Olson (git: willolson27)
 * Date Due: February 25, 2018
 *
 */
public class Knapsack {

	//create global fields
	static int[] testArr = {100, 100, 100, 100, 100, 100, 100, 100, 100, 3, 4, 5, 6, 7 ,8};
	
	//String constants
	private static final String ERROR = "Error: File not found";
	private static final String PROMPT = "Please provide a file name";
	private static final String OUTPUT_TXT = "knapsack.txt";
	private static final String DONE = "Done.";
	private static final String EMPTY_FILE = "This file is empty";
	private static final String POUND = "Pound";
	private static final String OBJECT = "Objects";
	private static final String OPTIMAL = "Optimal Capacity:";
	private static final String WEIGHTS = "Weights Used:";
	private static final String TEST = "Test.txt";
	private static final String TXT = ".txt";
	private static final String PLURAL = "s";
	
	//list of weights used
	private static ArrayList<Integer> masterList = new ArrayList<Integer>();
 	
	
	/**
	 * 
	 * Analyzes the optimal weight that can be placed in a knapsack, given a list of certain weights. Returns this optimal sum.
 	 * @param w - array of weights
	 * @param n - index in the weight array
	 * @param limit - maximum capacity of the knapsack
	 * @return - the optimal sum of all the weights than can be placed in the knapsack
	 */
	public static int knapsackSum(int[] w, int n, int limit) {

		//check the base case - index is zero
		if (n == 0) {
			return 0;
		}	

		//create two sums - one with the current element and one without
		int sum1 = (w[n - 1] + knapsackSum(w, n-1, limit - w[n-1]));
		int sum2 = (knapsackSum(w, n-1, limit));
		
		//check which of the two sums is greater and return it
		if (sum1 > sum2) {
			if (w[n-1] > limit) 
				return sum2;
			else
				return sum1;
		}
		else
			return sum2;
		
	}
	
	/**
	 * 
	 * Analyzes the optimal weight that can be placed in a knapsack, given a list of certain weights. Returns this optimal sum and generates a list of the weights that were used
	 * @param w - array of weights
	 * @param n - index in the weight array
	 * @param limit - maximum capacity of the knapsack
	 * @param list - list of weights used in the knapsack
	 * @return - the optimal sum of all the weights than can be placed in the knapsack
	 */ 
	public static int knapsackSum(int[] w, int n, int limit, List<Integer> list) {
		
		//create local fields
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		//check the base case - index is zero
		if (n == 0)
			return 0;
		
		//add the current element to a temp list
		list1.add(w[n-1]);
		
		//create two sums - one with the current element and one without
		int sum1 = (w[n-1] + knapsackSum(w, n-1, limit - w[n-1], list1));
		int sum2 = ( knapsackSum(w, n-1, limit, list2));
		
		//check which of the two sums is greater and return it
		if (sum1 > sum2) {
			if (w[n-1] > limit) {
				list.addAll(list2);
				return sum2;
			}
			else {
				list.addAll(list1);
				return sum1;
			}
		}
		else
			list.addAll(list2);
			return sum2;
		
		
	} 
	
	/**
	 * 
	 * reads in the main input file and returns a list of filenames found in it
	 * @param filename - name of file to be read from
	 * @return list of fileNames from a single input file
	 * @throws IOException
	 */
	public static ArrayList<String> getFileNames(String filename) throws IOException {
		
		//create local fields
		ArrayList<String> fileNames = new ArrayList<String>();
		
		//create the reader for the file with the test filenames in it
		BufferedReader inputReader = null;
	    try {
	    	inputReader = new BufferedReader(new FileReader(filename), 1024);
	    }
	    catch (FileNotFoundException e) {
	    	System.out.println(ERROR);
	    	System.exit(0);
	    }
	    
	    //create a list of file names
	   	String line;
	    while ((line = inputReader.readLine()) != null)
	      fileNames.add(line);
	    	
		return fileNames;
		
		
	}
	
	/**
	 * 
	 * reads in a given file and returns a list of integers found in it
	 * @param filename - name of file to be read from
	 * @return - list of Integers from an input file (in this case, weights for knapsack)
	 * @throws IOException
	 */
	public static ArrayList<Integer> readFile (String filename) throws IOException {
		
		//create local fields
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<Integer> ints = new ArrayList<Integer>();
		
		//create a reader for files with lists of weights in them
		BufferedReader inputReader = null;
	    try {
	    	inputReader = new BufferedReader(new FileReader(filename), 1024);
	    }
	    catch (FileNotFoundException e) {
	    	ints.add(-1);
	    	return ints;
	    }
	    
	    //read through the file line by line
	    String line;
	    while((line = inputReader.readLine()) != null) 
	    	lines.add(line);
	    
	    
	    //convert the lines to strings
	    for (String l : lines) {
	    	if (l.matches("\\d+"))
	    		ints.add(Integer.parseInt(l));
	    }
		return ints;
		
	}
	
	/**
	 * 
	 * runs the optimal knapsack solution for a given file and returns the results in a String
	 * @param f - filename to be read from
	 * @return - String representation of the result of running the knapsack program
	 * @throws IOException
	 */
	public static String knapsackResult (String f) throws IOException {
		
		//create local fields
		String toReturn = f;
		ArrayList<Integer> ints = readFile(f);
		String name;
		
		//check for irregularities in the files - file is empty or file was not found
		if (ints.isEmpty() || (ints.size() == 1 && ints.get(0) == 0)) {
			toReturn += "\n" + EMPTY_FILE;
			return toReturn + "\n";
		}
		else if ((ints.size() == 1 && ints.get(0) == -1)) {
			toReturn += "\n" + ERROR;
			return toReturn + "\n";
		}	
			
		//find the limit that was stored in the input file (should have been the first line)
		int limit = 0;
		if (ints.size() != 0)
			limit = ints.remove(0);
		
		//add the results of the list from readFile to an array
		int[] intArr = new int[ints.size()];
		int n = 0;
		for (int a: ints) {
			intArr[n] = a;
			n++;
		}
		
		//calculate the actual knapsack sum
		masterList.removeAll(masterList);
		int sum = knapsackSum(intArr, intArr.length, limit, masterList);
		if (f.contains(TEST))
			name = f.substring(0, f.indexOf(TEST));
		else
			name = f.substring(0, f.indexOf(TXT));
		String weights = intListToString(masterList, intArr, name);
		
		//add the results to the toReturn field
		toReturn += (" " + limit + "\t");	
		for (int i = 0; i < intArr.length; i++) {
			if (i == intArr.length - 1) {
				toReturn += (intArr[i]);
			}
			else
				toReturn += (intArr[i] + ",");
			n++;
		}
	    toReturn += "\n\n" + OPTIMAL + sum + "\n";
		toReturn += "\n" + WEIGHTS + "\n" + weights;
			 
		return toReturn + "\n";
	}
	
	/**
	 * 
	 * converts the list of resultant weights from knapsackSum and converts them to an output string
	 * @param usedWts - weights that were used in the knapsack of a certain file
	 * @param allWts - all the weights found in a certain file
	 * @param name - name of the object in the knapsack
	 * @return
	 */
	public static String intListToString(ArrayList<Integer> usedWts, int[] allWts, String name) {
		
		//create local fields
		String toReturn = "";
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		
		//traverse all the weights and add them to a map
		for (int i: allWts) 
			map.put(i, 0);
		//traverse all the weights used, and each time the same value is used, increment its value in the map
		for (Integer a: usedWts) 
			map.put(a, map.get(a) + 1);
		//print out the contents of the map
		for(Map.Entry<Integer,Integer> num : map.entrySet()) {
			if (num.getValue() != 1)
				toReturn += num.getValue() + " " + num.getKey() + " " + POUND +  " " + name + PLURAL + "\n";
			else
				toReturn += num.getValue() + " " + num.getKey() + " " + POUND +  " " + name + "\n";
		}	
		
		return toReturn;
		
	}
	
	
	/**
	 * 
	 * main method - tests out the knapsack program by calling other methods
	 * @param args - program arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		//create local fields
		ArrayList<String> fileNames;
		Scanner keyboard = new Scanner(System.in);
		
		//get names of files to be extracted
		if (args.length != 0 && args[0] != null)
			fileNames = getFileNames(args[0]);
		else {
			System.out.println(PROMPT);
			fileNames = getFileNames(keyboard.nextLine());
		}
		
		//Create print writer and print results to file
		PrintWriter out = new PrintWriter(new FileWriter(OUTPUT_TXT));
		for (String f : fileNames) {
			out.println(knapsackResult(f));
		} 

		//end
		out.close();
		System.out.println(DONE);
		
	}
	
	
	
	
}
