package SWEA.swea_14510;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 4
 * 2 3 10 5
 * 
 * 6 -> 14
 * 
 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] heights = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int maxH=0, minH=120;
			for(int i=0; i<N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
				if(heights[i] > maxH) maxH = heights[i];
				if(heights[i] < minH) minH = heights[i];
			}
			int gap = maxH-minH;
			System.out.println(gap);
			int res;
			res = (int) Math.ceil((double)gap/3);
//			System.out.println(res);
			res *= 2;
			if(gap%3 == 1) {
				res -= 1;
			}
			System.out.println("#" + tc + " " + res);
		}
	}
}
