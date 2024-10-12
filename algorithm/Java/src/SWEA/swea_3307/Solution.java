package SWEA.swea_3307;

import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder(T*5);
		for (int tc = 1; tc <= T; tc++) {
			int res = getLIS();
			sb.append("#"+tc+" "+res+"\n");
		}
		System.out.println(sb.toString());
	}
	static int getLIS() throws IOException{
		int N = Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		int[] C = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			numbers[i] = Integer.parseInt(st.nextToken());
		
		C[0] = numbers[0];
		int size = 1;
		
		for (int i = 1; i < N; i++) {
			if(numbers[i] > C[size-1]) C[size++] = numbers[i];
			else {
				int idx = Arrays.binarySearch(C, 0, size, numbers[i]);
				if(idx<0) {
					idx = Math.abs(idx)-1;
					C[idx] = numbers[i];
				}
			}
		}
//		System.out.println(Arrays.toString(Arrays.copyOf(C, size)));
		return size;
	}
}
