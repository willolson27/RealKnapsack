import java.util.List;

public class Knapsack {

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
	
	
	
	
}
