package BOJ.boj_14003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(-1000000000);
		arr.add(1);
		arr.add(1000000000);
		System.out.println(arr);
		System.out.println(-Collections.binarySearch(arr, 1000000001)-1);
	}

}
