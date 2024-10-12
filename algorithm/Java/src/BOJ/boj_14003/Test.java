package BOJ.boj_14003;

import java.util.Arrays;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
		TreeSet<int[]> ts = new TreeSet<>((a, b)-> Integer.compare(a[0], b[0]));
		ts.add(new int[] {1, 2, 3});
		ts.add(new int[] {2, 2, 3});
		ts.add(new int[] {1, 3, 4});
		ts.add(new int[] {2, 2, 4});
		while(!ts.isEmpty()) System.out.println(Arrays.toString(ts.pollFirst()));
	}

}
