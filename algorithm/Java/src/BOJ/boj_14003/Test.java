package BOJ.boj_14003;

import java.util.ArrayList;
import java.util.Collections;

public class Test {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(1);
		System.out.println(arr);
		int res = -Collections.binarySearch(arr, 3)-1;
		System.out.println(res);
	}

}
