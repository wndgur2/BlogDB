package SWEA.swea_5658;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;
	static char[] input;
	static TreeSet<Integer> decimals;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			input = br.readLine().toCharArray();
			decimals = new TreeSet<>();
			
			sb.append("#").append(tc).append(' ');
			solve();
		}
		System.out.println(sb.toString());
	}
	
	static void solve() {
		char[] sliding = new char[N+N/4-1];
		for (int i = 0; i < N; i++) sliding[i] = input[i];
		for (int i = 0; i < N/4-1; i++) sliding[N+i] = sliding[i];
		
		int r=N/4-1; // 1,2,3,4,5,6,7,8 (inclusive)
		
		String init="";
		for (int i = 0; i < N/4; i++) {
			init += input[i];
		}
		int number=Integer.parseInt(init, 16);
		decimals.add(number);
		while(r < N+N/4-2) {
			int newNumber = (int) ((int)number%(Math.pow(16, N/4-1))) * 16;
			newNumber += Integer.parseInt(Character.toString(sliding[++r]), 16);
			number = newNumber;
			decimals.add(number);
		}
		
		Iterator<Integer> iter = decimals.descendingIterator();
		int res = 0;
		for (int i = 0; i < K; i++)
			res = iter.next();
		sb.append(res).append('\n');
	}
	
}
