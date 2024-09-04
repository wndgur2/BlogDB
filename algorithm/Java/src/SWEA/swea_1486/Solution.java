package SWEA.swea_1486;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, minH, res;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] heights;
	
	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			minH = Integer.parseInt(st.nextToken());
			res = 200000;
			
			heights = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}
			
			subset(0, 0);
			System.out.println("#" + tc + " " + (res-minH));
		}
	}
	
	public static void subset(int depth, int sum) {
		if(depth==N) {
			if(sum<minH) return;
			if(sum<res) res = sum;
			return;
		}
		
		subset(depth+1, sum); // 비선택
		subset(depth+1, sum+heights[depth]); // 선택
	}

}
